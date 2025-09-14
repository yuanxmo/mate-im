package com.mate.im.user.infrastructure.exception;

import com.mate.im.base.exception.BizException;
import com.mate.im.base.exception.ExceptionCode;

/**
 * 用户异常
 *
 * @author yuanxmo
 */
public class UserException extends BizException {

    public UserException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public UserException(ExceptionCode exceptionCode, String message) {
        super(message, exceptionCode);
    }

    public UserException(ExceptionCode exceptionCode, String message, Throwable cause) {
        super(message, cause, exceptionCode);
    }

    public UserException(
            ExceptionCode exceptionCode, String message,
            Throwable cause, boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace, exceptionCode);
    }

    public UserException(Throwable cause, ExceptionCode exceptionCode) {
        super(cause, exceptionCode);
    }
}
