package com.iiseeuu.helper.entity;

import java.util.List;

/**
 * Author: 30453
 * Date: 2016/12/23 14:32
 */
public class Orders extends BaseList {

    private List<OrdersEntity> orders;

    public List<OrdersEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersEntity> orders) {
        this.orders = orders;
    }
}
