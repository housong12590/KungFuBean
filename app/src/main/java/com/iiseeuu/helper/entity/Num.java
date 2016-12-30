package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/28 09:44
 */
public class Num {

    private String name;
    private String count;
    private String newCount;

    public Num() {
    }

    public Num(String name , String count, String newCount) {
        this.name = name;
        this.count = count;
        this.newCount = newCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNewCount() {
        return newCount;
    }

    public void setNewCount(String newCount) {
        this.newCount = newCount;
    }
}
