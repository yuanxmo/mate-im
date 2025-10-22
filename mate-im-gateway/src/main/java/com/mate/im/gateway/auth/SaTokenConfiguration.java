package com.mate.im.gateway.auth;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.mate.im.api.user.constants.UserPermission;
import com.mate.im.api.user.constants.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SaTokenConfiguration {

    @Bean
    public SaReactorFilter saReactorFilter(){
        return new SaReactorFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico")
                .setAuth(object -> {
                    // 登录拦截 -- 拦截所有路由，并排除 /auth/login，开放登录
                    SaRouter.match("/**")
                            .notMatch("/auth/login")
                            .check(r -> StpUtil.checkLogin()
                    );
                    // 权限认证 -- 不同模块校验不同权限
                    SaRouter.match(
                            "/user/**",
                            r -> StpUtil.checkPermissionOr(
                                    UserPermission.BASIC.name(),
                                    UserPermission.FROZEN.name()
                            )
                    );
                })
                .setError(this::getSaResult);
    }

    private SaResult getSaResult(Throwable throwable) {
        switch (throwable) {
            case NotLoginException notLoginException:
                log.error("请先登录");
                return SaResult.error("请先登录");
            case NotRoleException notRoleException:
                if (UserRole.ADMIN.name().equals(notRoleException.getRole())) {
                    log.error("请勿越权使用！");
                    return SaResult.error("请勿越权使用！");
                }
                log.error("您无权限进行此操作！");
                return SaResult.error("您无权限进行此操作！");
            case NotPermissionException notPermissionException:
                if (UserPermission.GROUP_BASIC.name().equals(notPermissionException.getPermission())) {
                    log.error("无权限使用群聊！");
                    return SaResult.error("请申请群聊使用权限！");
                }
                log.error("您无权限进行此操作！");
                return SaResult.error("您无权限进行此操作！");
            default:
                return SaResult.error(throwable.getMessage());
        }
    }

}
