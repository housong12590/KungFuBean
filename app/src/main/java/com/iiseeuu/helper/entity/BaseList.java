package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/23 14:36
 */
public class BaseList {

    private String current_pager;
    private String total_pages;

    public String getCurrent_pager() {
        return current_pager;
    }

    public void setCurrent_pager(String current_pager) {
        this.current_pager = current_pager;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }
}
