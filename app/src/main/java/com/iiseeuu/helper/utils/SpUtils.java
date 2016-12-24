package com.iiseeuu.helper.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


public class SpUtils {

    private static Context context;
    private static String fileName;

    private static SharedPreferences getSharedPreferences() {
        if (context == null || TextUtils.isEmpty(fileName)) {
            new NullPointerException(SpUtils.class.getSimpleName() + " not init ");
        }
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static void init(Context context, String fileName) {
        SpUtils.context = context;
        SpUtils.fileName = fileName;
    }

    public static void put(String key, Object obj) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        if (obj instanceof String) {

            edit.putString(key, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(key, (Integer) obj);
        } else if (obj instanceof Boolean) {
            edit.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Long) {
            edit.putLong(key, (Long) obj);
        } else if (obj instanceof Float) {
            edit.putFloat(key, (Float) obj);
        }
        edit.apply();
    }

    public static int get(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }

    public static boolean get(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getSharedPreferences().getLong(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getSharedPreferences().getFloat(key, defValue);
    }

}
