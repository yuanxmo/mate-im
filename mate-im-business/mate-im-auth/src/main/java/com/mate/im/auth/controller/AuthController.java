package com.mate.im.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.mate.im.api.notice.constant.NoticeConstant;
import com.mate.im.api.notice.service.NoticeFacadeService;
import com.mate.im.api.user.service.UserFacadeService;
import com.mate.im.auth.exception.AuthException;
import com.mate.im.auth.exception.AuthExceptionCode;
import com.mate.im.auth.param.RegisterParam;
import com.mate.im.web.vo.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 认证相关接口
 *
 * @author yuanxmo
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Integer DEFAULT_LOGIN_SESSION_TIMEOUT = 60 * 60 * 24;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DubboReference(version="1.0.0")
    private UserFacadeService userFacadeService;

    @DubboReference(version="1.0.0")
    private NoticeFacadeService noticeFacadeService;

    @PostMapping("/register")
    public Result<Boolean> register(@Valid @RequestBody RegisterParam registerParam) {
        // 验证码校验
        String cachedCode = redisTemplate.opsForValue().get(NoticeConstant.CAPTCHA_KEY_PREFIX + registerParam.getEmail());
        if (!StringUtils.equalsIgnoreCase(cachedCode, registerParam.getCaptcha())) {
            throw new AuthException(AuthExceptionCode.VERIFICATION_CODE_WRONG);
        }

        // TODO 注册

        return null;
    }



    @PostMapping("/logout")
    public Result<Boolean> logout() {
        StpUtil.logout();
        return Result.success(true);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
