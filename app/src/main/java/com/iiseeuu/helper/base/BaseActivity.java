package com.iiseeuu.helper.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.http.LoadStatus;
import com.iiseeuu.helper.widget.OnRefreshListener;
import com.iiseeuu.helper.widget.loader.LoadingAndRetryManager;
import com.iiseeuu.helper.widget.loader.OnLoadingAndRetryListener;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Author: 30453
 * Date: 2016/12/22 17:43
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final int PAGE_SIZE = 20;
    public int CURRENT_PAGE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        parseIntent(getIntent());
        initView();
        setListener();
        initData();
        initDataFromNet(LoadStatus.LOADING);
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public void parseIntent(Intent intent) {

    }

    public void initDataFromNet(LoadStatus status) {

    }

    public void setListener() {

    }

    public LoadingAndRetryManager bindRefreshView(Object obj, OnRefreshListener listener) {
        if (obj instanceof PtrFrameLayout) {
            PtrFrameLayout ptr = (PtrFrameLayout) obj;
            PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
            ptr.setHeaderView(header);
            ptr.addPtrUIHandler(header);
            ptr.setPtrHandler(new PtrHandler() {
                @Override
                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                }

                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    if (listener != null) {
                        listener.onRefresh();
                    }
                }
            });
        }
        return new LoadingAndRetryManager(obj, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                retryView.findViewById(R.id.id_btn_retry).setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onRetry();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
