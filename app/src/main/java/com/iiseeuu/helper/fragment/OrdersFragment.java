package com.iiseeuu.helper.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.adapter.OrdersListAdapter;
import com.iiseeuu.helper.base.BaseFragment;
import com.iiseeuu.helper.entity.Orders;
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

/**
 * Author: 30453
 * Date: 2016/12/24 11:45
 */
public class OrdersFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener,
        BaseQuickAdapter.OnRecyclerViewItemClickListener {


    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;
    private OrdersListAdapter adapter;
    private LoadingAndRetryManager layoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_orders;
    }

    @Override
    public void initView() {
        layoutManager = bindRefreshView(ptrFrameLayout, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        adapter = new OrdersListAdapter(Collections.emptyList());
        adapter.openLoadMore(PAGE_SIZE, true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        adapter.setOnLoadMoreListener(this);
        adapter.setOnRecyclerViewItemClickListener(this);

    }

    @Override
    public void initData(LoadStatus status) {
        String accessToken = SpHelper.getAccessToken();
        CURRENT_PAGE = status == LoadStatus.MORE ? CURRENT_PAGE : 1;
        HttpModule.newHttpApi().getAllOrderList(accessToken, String.valueOf(CURRENT_PAGE), String.valueOf(PAGE_SIZE))
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
                                layoutManager.showEmpty();
                            } else {
                                adapter.setNewData(orders.getDesigns());
                                layoutManager.showContent();
                            }
                        }
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        CURRENT_PAGE++;
        initData(LoadStatus.MORE);
    }

    @Override
    public void onRefresh() {
        initData(LoadStatus.REFRESH);
    }

    @Override
    public void onRetry() {
        initData(LoadStatus.LOADING);
    }

    @Override
    public void onItemClick(View view, int i) {
//        OrdersEntity item = (OrdersEntity) adapter.getItem(i);
//        OrdersDetailActivity.start(getActivity(),item.getId());
    }
}
