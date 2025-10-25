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
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SaTokenConfigure {

    @Bean
    public SaReactorFilter saReactorFilter(){
        return new SaReactorFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico")
                .setAuth(object -> {
                    // 登录拦截 -- 拦截所有路由，并排除 /auth/login，开放登录
                    SaRouter.match("/**")
                            .notMatch("/auth/login")
                            .notMatch("/auth/register")
                            .notMatch("/auth/test")
                            .check(r -> StpUtil.checkLogin()
                    );
                    // TODO 权限认证 -- 不同模块校验不同权限
                    SaRouter.match("/admin/**", r -> StpUtil.checkRole(UserRole.ADMIN.name()));
                    SaRouter.match("/user/**", r -> StpUtil.checkPermissionOr(UserPermission.BASIC.name(), UserPermission.IM.name()));
                })
                .setError(this::getSaResult);
    }

    private SaResult getSaResult(Throwable throwable) {
        switch (throwable) {
            case NotLoginException notLoginException:
                return SaResult.error("请先登录");
            case NotRoleException notRoleException:
                return SaResult.error("无权限进行该操作");
            case NotPermissionException notPermissionException:
                return SaResult.error("无法进行此操作，请检查账号状态");
            default:
                return SaResult.error("未知错误，请联系管理员");
        }
    }

}
