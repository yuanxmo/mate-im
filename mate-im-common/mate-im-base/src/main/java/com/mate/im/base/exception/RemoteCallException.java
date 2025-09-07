package com.mate.im.base.exception;

/**
 * 远程调用异常
 *
 * @author yuanxmo
 */
public class RemoteCallException extends SystemException {

    public RemoteCallException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public RemoteCallException(ExceptionCode exceptionCode, String message) {
        super(exceptionCode, message);
    }

    public RemoteCallException(ExceptionCode exceptionCode, String message, Throwable cause) {
        super(exceptionCode, message, cause);
    }

    public RemoteCallException(ExceptionCode exceptionCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(exceptionCode, message, cause, enableSuppression, writableStackTrace);
    }

    public RemoteCallException(ExceptionCode exceptionCode, Throwable cause) {
        super(exceptionCode, cause);
    }
}
