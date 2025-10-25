package com.mate.im.auth.exception;

import com.mate.im.base.exception.ExceptionCode;

/**
 * 认证异常错误码
 *
 * @author yuanxmo
 */
public enum AuthExceptionCode implements ExceptionCode {
    /**
     * 用户状态不可用
     */
    USER_STATUS_IS_NOT_ACTIVE("USER_STATUS_IS_NOT_ACTIVE", "用户状态不可用"),

    /**
     * Token 场景不存在
     */
    TOKEN_SCENE_NOT_EXIST("TOKEN_SCENE_NOT_EXIST", "该场景不存在"),

    /**
     * Token key非法
     */
    TOKEN_KEY_IS_ILLEGAL("TOKEN_KEY_IS_ILLEGAL", "Token key非法"),

    /**
     * 验证码错误
     */
    VERIFICATION_CODE_WRONG("VERIFICATION_CODE_WRONG", "验证码错误"),

    /**
     * 用户信息查询失败
     */
    USER_QUERY_FAILED("USER_QUERY_FAILED", "用户信息查询失败"),

    /**
     * 用户未登录
     */
    USER_NOT_LOGIN("USER_NOT_LOGIN", "用户未登录"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在");

    private String code;

    private String message;

    AuthExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return "";
    }

    @Override
    public String getMessage() {
        return "";
    }
}
