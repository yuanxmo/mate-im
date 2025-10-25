package com.mate.im.base.exception;

/**
 * 业务异常错误码
 *
 * @author yuanxmo
 */
public enum BizExceptionCode implements ExceptionCode{
    /**
     * HTTP 客户端错误
     */
    HTTP_CLIENT_ERROR("HTTP_CLIENT_ERROR", "HTTP 客户端错误"),

    /**
     * HTTP 服务端错误
     */
    HTTP_SERVER_ERROR("HTTP_SERVER_ERROR", "HTTP 服务端错误"),

    /**
     * 状态机转换失败
     */
    STATE_MACHINE_TRANSITION_FAILED("STATE_MACHINE_TRANSITION_FAILED", "状态机转换失败"),

    /**
     * 重复请求
     */
    DUPLICATED("DUPLICATED", "重复请求"),

    /**
     * 远程调用返回结果为空
     */
    REMOTE_CALL_RESPONSE_IS_NULL("REMOTE_CALL_RESPONSE_IS_NULL", "远程调用返回结果为空"),

    /**
     * 远程调用返回结果失败
     */
    REMOTE_CALL_RESPONSE_IS_FAILED("REMOTE_CALL_RESPONSE_IS_FAILED", "远程调用返回结果失败"),

    SEND_NOTICE_DUPLICATED("SEND_NOTICE_DUPLICATED", "发送通知重复"),

    NOTICE_SAVE_FAILED("NOTICE_SAVE_FAILED", "通知保存失败"),

    NOTICE_TYPE_ERROR("NOTICE_TYPE_ERROR", "通知类型错误");



    private String code;

    private String message;

    BizExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
