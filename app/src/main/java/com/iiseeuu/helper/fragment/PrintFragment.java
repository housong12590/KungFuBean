package com.iiseeuu.helper.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.activity.PrintDetailActivity;
import com.iiseeuu.helper.adapter.PrintListAdapter;
import com.iiseeuu.helper.base.BaseFragment;
import com.iiseeuu.helper.entity.Prints;
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
 * Date: 2016/12/24 11:46
 */
public class PrintFragment extends BaseFragment implements OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;
    private LoadingAndRetryManager layoutManager;
    private PrintListAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_print;
    }

    @Override
    public void initView() {
        layoutManager = bindRefreshView(ptrFrameLayout, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        adapter = new PrintListAdapter(Collections.emptyList());
        adapter.openLoadMore(PAGE_SIZE, true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        adapter.setOnLoadMoreListener(this);
        adapter.setOnRecyclerViewItemClickListener((view, position) ->
                PrintDetailActivity.start(getActivity(), adapter.getItem(position)));
    }

    @Override
    public void initData(LoadStatus status) {
        String accessToken = SpHelper.getAccessToken();
        CURRENT_PAGE = status == LoadStatus.MORE ? CURRENT_PAGE : 1;
        HttpModule.newHttpApi().getPrintList(accessToken, String.valueOf(CURRENT_PAGE), String.valueOf(PAGE_SIZE))
                .compose(RxUtils.rxSchedulerHelper())
                .flatMap(new BaseRespFunc<>())
                .subscribe(new BaseSubscriber<Prints>(layoutManager, status) {
                    @Override
                    public void onNext(Prints prints) {
                        if (status == LoadStatus.MORE) {
                            boolean isNextLoad = CURRENT_PAGE < Integer.parseInt(prints.getTotal_pages());
                            adapter.notifyDataChangedAfterLoadMore(prints.getPrints(), isNextLoad);
                        } else {
                            if (prints.getPrints().isEmpty()) {
                                layoutManager.showEmpty();
                            } else {
                                adapter.setNewData(prints.getPrints());
                                layoutManager.showContent();
                            }
                        }
                    }
                });
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
    public void onLoadMoreRequested() {
        CURRENT_PAGE++;
        initData(LoadStatus.MORE);
    }
}
