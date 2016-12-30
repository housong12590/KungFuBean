package com.iiseeuu.helper.entity;

import java.util.List;

/**
 * Author: 30453
 * Date: 2016/12/23 14:32
 */
public class Orders extends BaseList {

    private List<OrdersEntity> designs;

    public List<OrdersEntity> getDesigns() {
        return designs;
    }

    public void setDesigns(List<OrdersEntity> designs) {
        this.designs = designs;
    }
}
