package com.iiseeuu.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.adapter.OrdersListAdapter;
import com.iiseeuu.helper.base.BaseActivity;
import com.iiseeuu.helper.entity.Orders;
import com.iiseeuu.helper.entity.OrdersEntity;
import com.iiseeuu.helper.entity.PrintEntity;
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
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class PrintDetailActivity extends BaseActivity implements OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.toolBar) Toolbar toolbar;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;
    private PrintEntity entity;
    private OrdersListAdapter adapter;
    private LoadingAndRetryManager layoutManager;

    public static void start(Context context, PrintEntity entity) {
        Intent intent = new Intent(context, PrintDetailActivity.class);
        intent.putExtra("entity", entity);
        context.startActivity(intent);
    }

    @Override
    public void parseIntent(Intent intent) {
        entity = (PrintEntity) intent.getSerializableExtra("entity");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_print_detail;
    }

    @Override
    public void initView() {
        toolbar.setTitle("打印机详情");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> finish());
        adapter = new OrdersListAdapter(Collections.emptyList());
        adapter.openLoadMore(PAGE_SIZE, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
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
        HttpModule.newHttpApi().getPrintOrderList(entity.getId(), accessToken, String.valueOf(CURRENT_PAGE), String.valueOf(PAGE_SIZE))
                .compose(RxUtils.rxSchedulerHelper())
                .flatMap(new BaseRespFunc<>())
                .subscribe(new BaseSubscriber<Orders>(layoutManager, status) {
                    @Override
                    public void onNext(Orders orders) {
                        if (status == LoadStatus.MORE) {
                            boolean isNextLoad = CURRENT_PAGE < Integer.parseInt(orders.getTotal_pages());
                            adapter.notifyDataChangedAfterLoadMore(orders.getDesigns(), isNextLoad);
                        } else {
                            if (orders.getDesigns().isEmpty()) {
                                orders.getDesigns().add(new OrdersEntity());
                            }
                            layoutManager.showContent();
                            adapter.setNewData(orders.getDesigns());
                        }
                    }
                });
    }


    @OnClick(R.id.tv_accreditUsers)
    public void onClick(View view) {
        AccreditUserActivity.start(this, entity.getId());
    }

    @Override
    public void onRefresh() {
        initDataFromNet(LoadStatus.REFRESH);
    }

    @Override
    public void onRetry() {
        initDataFromNet(LoadStatus.LOADING);
    }

    @Override
    public void onLoadMoreRequested() {
        CURRENT_PAGE++;
        initDataFromNet(LoadStatus.MORE);
    }
}
