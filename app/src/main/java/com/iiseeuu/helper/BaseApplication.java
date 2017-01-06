package com.iiseeuu.helper;

import android.app.Application;
import android.content.Context;

import com.iiseeuu.helper.utils.SpUtils;

import java.util.HashMap;
import java.util.Map;


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

    public static Map<String, Integer> getPrintIcon() {
        Map<String, Integer> map = new HashMap<>();
        map.put("L111", R.drawable.l111);
        map.put("L130", R.drawable.l130);
        map.put("L211", R.drawable.l211);
        map.put("L220", R.drawable.l220);
        map.put("L310", R.drawable.l310);
        map.put("L313", R.drawable.l313);
        map.put("L353", R.drawable.l353);
        map.put("L358", R.drawable.l358);
        map.put("L360", R.drawable.l360);
        map.put("L363", R.drawable.l363);
        map.put("L365", R.drawable.l365);
        map.put("L383", R.drawable.l383);
        map.put("L385", R.drawable.l385);
        map.put("L455", R.drawable.l455);
        map.put("L485", R.drawable.l485);
        map.put("L551", R.drawable.l551);
        map.put("L558", R.drawable.l558);
        map.put("L565", R.drawable.l565);
        map.put("L801", R.drawable.l801);
        map.put("L805", R.drawable.l805);
        map.put("L810", R.drawable.l810);
        map.put("L850", R.drawable.l850);
        return map;
    }

}
