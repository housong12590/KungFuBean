package com.iiseeuu.helper.http;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: 30453
 * Date: 2017/1/4 16:23
 */
public class HttpParams {

    public static Map<String, String> getUserList(String accessToken, String type, String page, String pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        if (!TextUtils.isEmpty(type)) {
            params.put("type", type);
        }
        params.put("page", page);
        params.put("page_size", pageSize);
        return params;
    }
}
