package com.iiseeuu.helper;

import android.app.Application;
import android.content.Context;

import com.iiseeuu.helper.utils.SpUtils;


/**
 * Author: 30453
 * Date: 2016/10/21 14:09
 */
public class BaseApplication extends Application {

    private static Context context;
    private static final String SHARE_FILE_NAME = "user_info_data";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        SpUtils.init(this, SHARE_FILE_NAME);
    }

    public static Context getContext() {
        return context;
    }

}
