package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/23 14:33
 */
public class BaseResp<T> {

    private String status_code;
    private String msg;
    private T data;

    public String getStatusCode() {
        return status_code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
