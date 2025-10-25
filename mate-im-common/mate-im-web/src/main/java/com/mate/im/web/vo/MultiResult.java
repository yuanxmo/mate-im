package com.mate.im.web.vo;

import com.mate.im.base.response.ResponseCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yuanxmo
 */
@Getter
@Setter
public class MultiResult<T> extends Result<List<T>> {
    /**
     * 总记录数
     */
    private long total;

    /**
     * 当前页码
     */
    private int page;

    /**
     * 每页记录数
     */
    private int size;

    public MultiResult() {
        super();
    }

    public MultiResult(Boolean success, String code, String message, List<T> data, long total, int page, int size) {
        super(success, code, message, data);
        this.total = total;
        this.page = page;
        this.size = size;
    }

    public static <T> MultiResult<T> success(List<T> data, long total, int page, int size) {
        return new MultiResult<>(true, ResponseCode.SUCCESS.name(), ResponseCode.SUCCESS.name(), data, total, page, size);
    }

    public static <T> MultiResult<T> errorMulti(String errorCode, String errorMessage) {
        return new MultiResult<>(false, errorCode, errorMessage, null, 0, 0, 0);
    }

}
