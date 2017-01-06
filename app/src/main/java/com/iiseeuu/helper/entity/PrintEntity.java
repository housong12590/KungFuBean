package com.iiseeuu.helper.entity;

import java.io.Serializable;

/**
 * Author: 30453
 * Date: 2016/12/23 14:39
 */
public class PrintEntity implements Serializable {


    private String id;
    private String name;
    private String alias;
    private String type;
    private String user_nickname;
    private String created_at;
    private String designs_count;
    private String today_designs_count;
    private boolean online;
    private boolean is_auto;
    private String icon;
    private String pc_os;
    private String pc_os_version;
    private String pc_cpu;
    private String pc_memory;
    private String pc_storage;
    private String last_active_time;
    private String active_ip;

    public String getCreatedAt() {
        return created_at;
    }

    public boolean isAuto() {
        return is_auto;
    }

    public String getType() {
        return type;
    }

    public String getPcOs() {
        return pc_os;
    }

    public String getPcOsVersion() {
        return pc_os_version;
    }

    public String getPcCpu() {
        return pc_cpu;
    }

    public String getPcMemory() {
        return pc_memory;
    }

    public String getPcStorage() {
        return pc_storage;
    }

    public String getLastActiveTime() {
        return last_active_time;
    }

    public String getActiveIp() {
        return active_ip;
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

    public String getDesignsCount() {
        return designs_count;
    }

    public String getTodayDesignsCount() {
        return today_designs_count;
    }

    public boolean isOnline() {
        return online;
    }
}
