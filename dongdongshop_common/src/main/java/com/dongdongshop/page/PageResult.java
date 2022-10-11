package com.dongdongshop.page;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private Integer pageNum;/*当前页数*/
    private Integer pageSize;/*每页条数*/
    private List<T> rows;/*值*/
    private Long total;/*总条数*/
    private Integer pages;/*总页数*/

    public PageResult(Integer pageNum, Integer pageSize, List<T> rows, Long total, Integer pages) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.rows = rows;
        this.total = total;
        this.pages = pages;
    }

    public PageResult() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}