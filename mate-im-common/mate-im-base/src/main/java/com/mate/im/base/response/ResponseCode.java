package com.mate.im.base.response;

/**
 * 响应返回码
 *
 * @author yuanxmo
 */
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS,

    /**
     * 重复
     */
    DUPLICATE,

    /**
     * 非法参数
     */
    ILLEGAL_ARGUMENT,

    /**
     * 系统错误
     */
    SYSTEM_ERROR,

    /**
     * 业务错误
     */
    BIZ_ERROR;
}
