package com.iiseeuu.helper.entity;

import java.io.Serializable;

/**
 * Author: 30453
 * Date: 2016/12/23 14:39
 */
public class PrintEntity implements Serializable{


    private String id;
    private String name;
    private String alias;
    private String user_nickname;
    private String total_print_count;
    private String today_print_count;
    private boolean online;
    private String icon;
    private String pc_os;
    private String pc_os_version;
    private String pc_cpu;
    private String pc_memory;
    private String pc_storage;
    private String last_active_time;
    private String last_active_ip;


    public String getPc_os() {
        return pc_os;
    }

    public String getPc_os_version() {
        return pc_os_version;
    }

    public String getPc_cpu() {
        return pc_cpu;
    }

    public String getPc_memory() {
        return pc_memory;
    }

    public String getPc_storage() {
        return pc_storage;
    }

    public String getLast_active_time() {
        return last_active_time;
    }

    public String getLast_active_ip() {
        return last_active_ip;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getNickname() {
        return user_nickname;
    }

    public String getTotalPrintCount() {
        return total_print_count;
    }

    public String getTodayPrintCount() {
        return today_print_count;
    }

    public boolean isOnline() {
        return online;
    }
}
