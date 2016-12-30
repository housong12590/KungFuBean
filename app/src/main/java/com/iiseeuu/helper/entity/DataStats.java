package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/23 15:05
 */
public class DataStats {

    private String users_count;
    private String users_today_count;
    private String orders_count;
    private String orders_today_count;
    private String prints_count;
    private String prints_today_count;

    public String getUsersCount() {
        return users_count == null ? "0" : users_count;
    }

    public String getUsersTodayCount() {
        return users_today_count == null ? "0" : users_today_count;
    }

    public String getOrdersCount() {
        return orders_count == null ? "0" : orders_count;
    }

    public String getOrdersTodayCount() {
        return orders_today_count == null ? "0" : orders_today_count;
    }

    public String getPrintsCount() {
        return prints_count == null ? "0" : prints_count;
    }

    public String getPrintsTodayCount() {
        return prints_today_count == null ? "0" : prints_today_count;
    }
}
