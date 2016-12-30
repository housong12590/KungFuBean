package com.iiseeuu.helper.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: 30453
 * Date: 2016/12/30 17:17
 */
public class TimeUtils {

    public static float dateToDay(String date) {
        long parseLong = 1;
        if (!TextUtils.isEmpty(date) && TextUtils.isDigitsOnly(date)) {
            parseLong = Long.parseLong(date) * 1000;
            return Float.parseFloat(new SimpleDateFormat("dd").format(new Date(parseLong)));
        }
        return 0;
    }
}
