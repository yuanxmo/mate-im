package com.mate.im.auth.param;

import com.mate.im.base.validator.IsEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 注册表单
 *
 * @author yuanxmo
 */
@Getter
@Setter
public class RegisterParam {

    /**
     * 邮箱地址
     */
    @IsEmail
    private String email;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    /**
     * 邀请码
     */
    private String inviteCode;
}
