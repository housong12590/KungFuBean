package com.iiseeuu.helper.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 30453
 * Date: 2016/12/23 14:55
 */
public class Users extends BaseList {

    private List<UserEntity> users;
    private String users_count;

    public String getUserCount() {
        return users_count;
    }

    public List<UserEntity> getUsers() {
        if(users == null){
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
