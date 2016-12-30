package com.iiseeuu.helper.utils;

/**
 * Author: 30453
 * Date: 2016/12/23 15:48
 */
public class SpHelper {

    public static String getAccessToken() {
        return SpUtils.get("access_token", "");
    }

    public static void setAccessToken(String token) {
        SpUtils.put("access_token", token);
    }

    public static void setAutoLogin(boolean autoLogin) {
        SpUtils.put("autoLogin", autoLogin);
    }

    public static boolean isAutoLogin() {
        return SpUtils.get("autoLogin", false);
    }

    public static void setRememberPassword(boolean rememberPassword) {
        SpUtils.put("rememberPassword", rememberPassword);
    }

    public static boolean isRememberPassword() {
        return SpUtils.get("rememberPassword", true);
    }

    public static void setUserName(String userName) {
        SpUtils.put("userName", userName);
    }

    public static String getUserName() {
        return SpUtils.get("userName", "");
    }

    public static void setPassword(String password) {
        SpUtils.put("password", password);
    }

    public static String getPassword() {
        return SpUtils.get("password", "");
    }

}
