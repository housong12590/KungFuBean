package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/23 14:39
 */
public class FacilityEntity {

    private String id;
    private String type;
    private String alias;
    private String icon;
    private boolean is_online;
    private String total_print_count;
    private String today_print_count;
    private String bind_weChat_user;
    private String create_time;
    private String change_time;
    private String active_ip;
    private String pc_mac_address;
    private String pc_params;
    private String version;
    private String activation_code;

    public String getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public String getAlias(){
        return alias;
    }

    private String getIcon(){
        return icon;
    }

    public boolean isOnline(){
        return is_online;
    }

    public String getTotalPrintCount(){
        return total_print_count;
    }

    public String getTodayPrintCount(){
        return today_print_count;
    }

    public String getBindWeChatUser() {
        return bind_weChat_user;
    }

    public String getCreateTime() {
        return create_time;
    }

    public String getChangeTime() {
        return change_time;
    }

    public String getActiveIp() {
        return active_ip;
    }

    public String getPcMacAddress() {
        return pc_mac_address;
    }

    public String getPcParams() {
        return pc_params;
    }

    public String getVersion() {
        return version;
    }

    public String getActivationCode() {
        return activation_code;
    }
}
