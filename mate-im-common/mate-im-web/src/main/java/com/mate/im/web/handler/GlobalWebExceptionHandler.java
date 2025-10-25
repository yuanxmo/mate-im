package com.mate.im.web.handler;

import com.google.common.collect.Maps;
import com.mate.im.base.exception.BizException;
import com.mate.im.base.exception.SystemException;
import com.mate.im.base.response.ResponseCode;
import com.mate.im.web.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * 全局异常处理器
 *
 * @author yuanxmo
 */
@Slf4j
@ControllerAdvice
public class GlobalWebExceptionHandler {

    /**
     * 参数校验异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException occurred.", ex);
        Map<String, String> errors = Maps.newHashMapWithExpectedSize(1);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * 自定义业务异常处理器
     *
     * @param bizException
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result exceptionHandler(BizException bizException) {
        log.error("BizException occurred.", bizException);
        Result result = new Result();
        result.setCode(bizException.getExceptionCode().getCode());
        if (bizException.getMessage() == null) {
            result.setMessage(bizException.getExceptionCode().getMessage());
        } else {
            result.setMessage(bizException.getMessage());
        }
        result.setSuccess(false);
        return result;
    }

    /**
     * 自定义系统异常处理器
     *
     * @param systemException
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result exceptionHandler(SystemException systemException) {
        log.error("SystemException occurred.", systemException);
        Result result = new Result();
        result.setCode(systemException.getExceptionCode().getCode());
        if (systemException.getMessage() == null) {
            result.setMessage(systemException.getExceptionCode().getMessage());
        } else {
            result.setMessage(systemException.getMessage());
        }
        result.setSuccess(false);
        return result;
    }

    /**
     * 自定义系统异常处理器
     * @param throwable
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result throwableHandler(Throwable throwable) {
        log.error("Throwable occurred.", throwable);
        Result result = new Result();
        result.setCode(ResponseCode.SYSTEM_ERROR.name());
        result.setMessage("当前网络拥挤，请您稍后再试~");
        result.setSuccess(false);
        return result;
    }
}
