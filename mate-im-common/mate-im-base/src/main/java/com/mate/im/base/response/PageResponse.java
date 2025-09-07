package com.mate.im.base.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页通用出参
 *
 * @author yuanxmo
 */
@Getter
@Setter
public class PageResponse<T> extends MultiResponse<T>  {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页结果数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 总数
     */
    private Integer total;

    public static <T> PageResponse<T> of(List<T> datas, int total, int pageSize, int currentPage) {
        PageResponse<T> pageResponse = new PageResponse<T>();
        pageResponse.setSuccess(true);
        pageResponse.setDatas(datas);
        pageResponse.setTotal(total);
        pageResponse.setPageSize(pageSize);
        pageResponse.setCurrentPage(currentPage);
        pageResponse.setTotalPage((pageSize + total - 1) / pageSize);
        return pageResponse;
    }
}
