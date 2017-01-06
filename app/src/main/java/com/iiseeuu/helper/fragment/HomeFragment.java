package com.iiseeuu.helper.fragment;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iiseeuu.helper.Constant;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.activity.TendencyActivity;
import com.iiseeuu.helper.adapter.HomePageAdapter;
import com.iiseeuu.helper.base.BaseFragment;
import com.iiseeuu.helper.entity.DataStats;
import com.iiseeuu.helper.entity.Num;
import com.iiseeuu.helper.http.BaseRespFunc;
import com.iiseeuu.helper.http.BaseSubscriber;
import com.iiseeuu.helper.http.HttpModule;
import com.iiseeuu.helper.http.LoadStatus;
import com.iiseeuu.helper.utils.RxUtils;
import com.iiseeuu.helper.utils.SpHelper;
import com.iiseeuu.helper.widget.OnRefreshListener;
import com.iiseeuu.helper.widget.RecyclerSpace;
import com.iiseeuu.helper.widget.loader.LoadingAndRetryManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: 30453
 * Date: 2016/12/23 18:05
 */
public class HomeFragment extends BaseFragment implements BaseQuickAdapter.OnRecyclerViewItemClickListener, OnRefreshListener {

    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;
    private HomePageAdapter adapter;
    private LoadingAndRetryManager layoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        layoutManager = bindRefreshView(ptrFrameLayout, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.addItemDecoration(new RecyclerSpace(3, Color.parseColor("#e0e0e0"), 1));
        adapter = new HomePageAdapter(handleData(null));
        adapter.setOnRecyclerViewItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initData(LoadStatus status) {
        HttpModule.newHttpApi().getDataStats(SpHelper.getAccessToken()).compose(RxUtils.rxSchedulerHelper())
                .flatMap(new BaseRespFunc<>()).subscribe(new BaseSubscriber<DataStats>(layoutManager,status) {
            @Override
            public void onNext(DataStats response) {
                adapter.setNewData(handleData(response));
                layoutManager.showContent();
            }
        });
    }

    private List<Num> handleData(DataStats response) {
        if (response == null) {
            response = new DataStats();
        }
        List<Num> list = new ArrayList<>();
        list.add(new Num("订单", response.getOrdersCount(), response.getOrdersTodayCount()));
        list.add(new Num("用户", response.getUsersCount(), response.getUsersTodayCount()));
        list.add(new Num("打印机", response.getPrintsCount(), response.getPrintsTodayCount()));
        return list;
    }

    @Override
    public void onItemClick(View view, int i) {
        switch (i) {
            case 0: // 订单
                TendencyActivity.start(getActivity(), Constant.ORDERS_KEY, Constant.ORDERS_NAME);
                break;
            case 1: // 用户
                TendencyActivity.start(getActivity(), Constant.USERS_KEY, Constant.USERS_NAME);
                break;
            case 2: //打印机
                TendencyActivity.start(getActivity(), Constant.PRINTS_KEY, Constant.PRINTS_NAME);
                break;
        }
    }

    @Override
    public void onRefresh() {
        initData(LoadStatus.REFRESH);
    }

    @Override
    public void onRetry() {
        initData(LoadStatus.LOADING);
    }
}
