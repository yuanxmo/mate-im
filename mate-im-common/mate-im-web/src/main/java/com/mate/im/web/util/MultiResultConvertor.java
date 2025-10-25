package com.mate.im.web.util;


import com.mate.im.base.response.PageResponse;
import com.mate.im.base.response.ResponseCode;
import com.mate.im.web.vo.MultiResult;

/**
 * @author yuanxmo
 */
public class MultiResultConvertor {
    public static <T> MultiResult<T> convert(PageResponse<T> pageResponse) {
        return new MultiResult<>(
                true, ResponseCode.SUCCESS.name(), ResponseCode.SUCCESS.name(),
                pageResponse.getDatas(),
                pageResponse.getTotal(),
                pageResponse.getCurrentPage(),
                pageResponse.getPageSize()
        );
    }
}
