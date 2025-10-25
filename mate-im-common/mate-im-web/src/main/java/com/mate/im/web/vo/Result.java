package com.mate.im.web.vo;

import com.mate.im.base.response.ResponseCode;
import com.mate.im.base.response.SingleResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuanxmo
 */
@Getter
@Setter
public class Result<T> {

    private String code;

    private Boolean success;

    private String message;

    private T data;

    public Result() {}

    public Result(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(Boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(SingleResponse<T> singleResponse) {
        this.success = singleResponse.getSuccess();
        this.code = singleResponse.getResponseCode();
        this.message = singleResponse.getResponseMessage();
        this.data = singleResponse.getData();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, ResponseCode.SUCCESS.name(), data);
    }

    public static <T> Result<T> error(String errorCode, String errorMessage) {
        return new Result<>(false, errorCode, errorMessage, null);
    }

}
