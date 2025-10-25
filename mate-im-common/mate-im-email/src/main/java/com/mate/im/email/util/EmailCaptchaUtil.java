package com.mate.im.email.util;

import com.mate.im.email.constants.EmailConstant;
import com.mate.im.email.constants.EmailType;

public class EmailCaptchaUtil {
    private EmailCaptchaUtil() {}

    /**
     * 验证码类型
     */
    public static final String TYPE = "TYPE";
    /**
     * 验证码 KEY
     */
    public static final String CAPTCHA = "CAPTCHA";
    /**
     * 验证码有效时间，分钟
     */
    public static final String EXPIRY_TIME = "EXPIRY_TIME";

    /**
     * 获取验证码缓存 key
     * @param emailType
     * @return
     */
    public static final String getType(EmailType emailType) {
        return switch (emailType) {
            case REGISTER_CAPTCHA -> "注册";
            case LOGIN_CAPTCHA -> "登录";
            case PASSWORD_RESET_CAPTCHA -> "密码重置";
            case FORGET_PASSWORD_CAPTCHA -> "忘记密码";
            default -> null;
        };
    }

    public static String getSubject(EmailType emailType) {
        return switch (emailType) {
            case REGISTER_CAPTCHA -> EmailConstant.REGISTER_CAPTCHA_SUBJECT.getName();
            case LOGIN_CAPTCHA -> EmailConstant.LOGIN_CAPTCHA_SUBJECT.getName();
            case PASSWORD_RESET_CAPTCHA -> EmailConstant.PASSWORD_RESET_SUBJECT.getName();
            case FORGET_PASSWORD_CAPTCHA -> EmailConstant.FORGET_PASSWORD_CAPTCHA_SUBJECT.getName();
            default -> null;
        };
    }
}
