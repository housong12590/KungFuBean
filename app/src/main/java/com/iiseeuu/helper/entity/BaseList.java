package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/23 14:36
 */
public class BaseList {

    private String count;
    private String current_pager;
    private String total_page;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCurrent_pager() {
        return current_pager;
    }

    public void setCurrent_pager(String current_pager) {
        this.current_pager = current_pager;
    }

    public String getTotal_page() {
        return total_page;
    }

    public void setTotal_page(String total_page) {
        this.total_page = total_page;
    }
}
