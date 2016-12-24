package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/23 14:41
 */
public class OrdersEntity {

    private String id;
    private String print_time;
    private String create_time;
    private String image_url;
    private String create_city;
    private String we_chat_version;
    private FacilityEntity facility;
    private UserEntity user;

    public String getId() {
        return id;
    }

    public String getPrintTime() {
        return print_time;
    }

    public String getCreateTime() {
        return create_time;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getCreateCity() {
        return create_city;
    }

    public String getWeChatVersion() {
        return we_chat_version;
    }

    public FacilityEntity getFacility() {
        return facility;
    }

    public UserEntity getUser() {
        return user;
    }
}
