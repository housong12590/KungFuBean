package com.iiseeuu.helper.activity;

import android.content.Intent;
import android.view.WindowManager;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseActivity;
import com.iiseeuu.helper.utils.RxUtils;
import com.iiseeuu.helper.utils.SpHelper;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Author: 30453
 * Date: 2016/12/29 14:51
 */
public class SplashActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Observable.timer(1, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).subscribe(mLong -> {
            Class<?> clazz = SpHelper.isAutoLogin() ? MainActivity.class : LoginActivity.class;
            startActivity(new Intent(this, clazz));
            finish();
        });
    }
}
