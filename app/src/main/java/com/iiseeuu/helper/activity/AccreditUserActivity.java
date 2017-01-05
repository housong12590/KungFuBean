package com.iiseeuu.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.adapter.AccreditUserAdapter;
import com.iiseeuu.helper.base.BaseActivity;
import com.iiseeuu.helper.entity.Users;
import com.iiseeuu.helper.http.BaseRespFunc;
import com.iiseeuu.helper.http.BaseSubscriber;
import com.iiseeuu.helper.http.HttpModule;
import com.iiseeuu.helper.http.LoadStatus;
import com.iiseeuu.helper.utils.RxUtils;
import com.iiseeuu.helper.utils.SpHelper;
import com.iiseeuu.helper.widget.DividerItemDecoration;
import com.iiseeuu.helper.widget.OnRefreshListener;
import com.iiseeuu.helper.widget.loader.LoadingAndRetryManager;

import java.util.Collections;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class AccreditUserActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {

    @Bind(R.id.toolBar) Toolbar toolbar;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;
    private String id;
    private AccreditUserAdapter adapter;
    private LoadingAndRetryManager layoutManager;

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, AccreditUserActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public void parseIntent(Intent intent) {
        id = intent.getStringExtra("id");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_accredit_user;
    }

    @Override
    public void initView() {
        toolbar.setTitle("授权用户");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> finish());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        adapter = new AccreditUserAdapter(Collections.emptyList());
        adapter.openLoadMore(PAGE_SIZE, true);
        recyclerView.setAdapter(adapter);
        layoutManager = bindRefreshView(ptrFrameLayout, this);
    }

    @Override
    public void setListener() {
        adapter.setOnLoadMoreListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initDataFromNet(LoadStatus status) {
        String accessToken = SpHelper.getAccessToken();
        CURRENT_PAGE = status == LoadStatus.MORE ? CURRENT_PAGE : 1;
        HttpModule.newHttpApi().getAccreditUserList(id, accessToken, String.valueOf(CURRENT_PAGE), String.valueOf(PAGE_SIZE))
                .compose(RxUtils.rxSchedulerHelper())
                .flatMap(new BaseRespFunc<>())
                .subscribe(new BaseSubscriber<Users>(layoutManager, status) {
                    @Override
                    public void onNext(Users users) {
                        if (status == LoadStatus.MORE) {
                            boolean isNextLoad = CURRENT_PAGE < Integer.parseInt(users.getTotal_pages());
                            adapter.notifyDataChangedAfterLoadMore(users.getUsers(), isNextLoad);
                        } else {
                            if (users.getUsers().isEmpty()) {
                                layoutManager.showEmpty();
                            } else {
                                adapter.setNewData(users.getUsers());
                                layoutManager.showContent();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                        super.onError(e);
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        CURRENT_PAGE++;
        initDataFromNet(LoadStatus.MORE);
    }

    @Override
    public void onRefresh() {
        initDataFromNet(LoadStatus.REFRESH);
    }

    @Override
    public void onRetry() {
        initDataFromNet(LoadStatus.LOADING);
    }
}
