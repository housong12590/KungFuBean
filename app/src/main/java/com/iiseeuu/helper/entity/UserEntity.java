package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/23 14:40
 */
public class UserEntity {

    private String id;
    private String name;
    private String avatar;
    private String print_count;
    private boolean is_owner;
    private String gender;
    private String age;
    private boolean isAttention;
    private String recently_active_time;
    private String recently_active_city;
    private String create_time;
    private FacilityEntity facility;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPrintCount() {
        return print_count;
    }

    public boolean isOwner() {
        return is_owner;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public String getRecentlyActiveTime() {
        return recently_active_time;
    }

    public String getRecentlyActiveCity() {
        return recently_active_city;
    }

    public String getCreateTime() {
        return create_time;
    }

    public FacilityEntity getFacility() {
        return facility;
    }
}
