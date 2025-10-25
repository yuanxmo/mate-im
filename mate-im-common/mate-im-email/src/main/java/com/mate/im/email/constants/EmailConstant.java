package com.mate.im.email.constants;

public enum EmailConstant {
    /**
     * 邮件发信人
     */
    EMAIL_FORM_NAME("mate-im"),

    /**
     * 邮件发信人地址
     */
    EMAIL_FORM_ADDRESS("1764187345@qq.com"),

    /**
     * 邮件模板路径
     */
    CAPTCHA_TEMPLATE("captchaTemplate.html"),

    /**
     * 注册验证码邮件主题
     */
    REGISTER_CAPTCHA_SUBJECT("mate-im: 注册验证码"),

    /**
     * 登录验证码邮件主题
     */
    LOGIN_CAPTCHA_SUBJECT("mate-im: 登录验证码"),

    /**
     * 忘记密码验证码邮件主题
     */
    FORGET_PASSWORD_CAPTCHA_SUBJECT("mate-im: 忘记密码"),

    /**
     * 密码重置邮件主题
     */
    PASSWORD_RESET_SUBJECT("mate-im: 密码重置"),

    ;

    private String name;

    EmailConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
