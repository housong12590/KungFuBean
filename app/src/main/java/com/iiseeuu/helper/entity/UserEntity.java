package com.iiseeuu.helper.entity;

import java.io.Serializable;

/**
 * Author: 30453
 * Date: 2016/12/23 14:40
 */
public class UserEntity implements Serializable {

    private String id;
    private String nickname;
    private String headimgurl;
    private String prints_count;
    private boolean is_owner;
    private boolean subscribe;
    private String created_at;
    private String sex;
    private String recently_print_time;

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public String getPrints_count() {
        return prints_count;
    }

    public boolean is_owner() {
        return is_owner;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getSex() {
        return sex == null ? "未知" : sex.equals("1") ? "男" : "女";
    }

    public String getRecently_print_time() {
        return recently_print_time;
    }
}
