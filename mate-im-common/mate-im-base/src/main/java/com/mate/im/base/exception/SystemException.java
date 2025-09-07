package com.mate.im.base.exception;

/**
 * 系统异常错误码
 *
 * @author yuanxmo
 */
public class SystemException extends RuntimeException {

    private ExceptionCode exceptionCode;

    public SystemException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public SystemException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public SystemException(ExceptionCode exceptionCode, String message, Throwable cause) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public SystemException(ExceptionCode exceptionCode, Throwable cause) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public SystemException(ExceptionCode exceptionCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
    }

    public void setExceptionCode(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
