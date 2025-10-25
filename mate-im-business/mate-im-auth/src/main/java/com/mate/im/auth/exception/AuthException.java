package com.mate.im.auth.exception;

import com.mate.im.base.exception.BizException;
import com.mate.im.base.exception.ExceptionCode;

public class AuthException extends BizException {
    public AuthException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public AuthException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }

    public AuthException(String message, Throwable cause, ExceptionCode exceptionCode) {
        super(message, cause, exceptionCode);
    }

    public AuthException(Throwable cause, ExceptionCode exceptionCode) {
        super(cause, exceptionCode);
    }

    public AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionCode exceptionCode) {
        super(message, cause, enableSuppression, writableStackTrace, exceptionCode);
    }
}
