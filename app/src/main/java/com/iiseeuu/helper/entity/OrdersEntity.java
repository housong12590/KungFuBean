package com.iiseeuu.helper.entity;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.iiseeuu.helper.adapter.OrdersListAdapter;

/**
 * Author: 30453
 * Date: 2016/12/23 14:41
 */
public class OrdersEntity extends MultiItemEntity {

    private String id;
    private String finish_url;
    private String created_at;
    private String print_id;
    private String print_name;
    private String city;
    private String nickname;
    private String headimgurl;

    public String getId() {
        return id;
    }

    public String getFinishUrl() {
        return finish_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getPrintId() {
        return print_id;
    }

    public String getPrintName() {
        return print_name;
    }

    public String getCity() {
        return city;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    @Override
    public int getItemType() {
        return TextUtils.isEmpty(id) ? OrdersListAdapter.EMPTY_ITEM : OrdersListAdapter.NORMAL_ITEM;
    }
}
