package com.iiseeuu.helper.utils;

import android.widget.Toast;

import com.iiseeuu.helper.BaseApplication;

/**
 * Author: 30453
 * Date: 2016/12/23 15:45
 */
public class ToastUtils {

    public static void show(String msg){
        Toast.makeText(BaseApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
