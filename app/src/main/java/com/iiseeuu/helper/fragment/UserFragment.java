package com.iiseeuu.helper.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.activity.UserDetailActivity;
import com.iiseeuu.helper.adapter.UserListAdapter;
import com.iiseeuu.helper.base.BaseFragment;
import com.iiseeuu.helper.entity.UserEntity;
import com.iiseeuu.helper.entity.Users;
import com.iiseeuu.helper.http.BaseRespFunc;
import com.iiseeuu.helper.http.BaseSubscriber;
import com.iiseeuu.helper.http.HttpModule;
import com.iiseeuu.helper.http.HttpParams;
import com.iiseeuu.helper.http.LoadStatus;
import com.iiseeuu.helper.utils.RxUtils;
import com.iiseeuu.helper.utils.SpHelper;
import com.iiseeuu.helper.widget.CustomPopWindow;
import com.iiseeuu.helper.widget.DividerItemDecoration;
import com.iiseeuu.helper.widget.OnRefreshListener;
import com.iiseeuu.helper.widget.TitleLayout;
import com.iiseeuu.helper.widget.loader.LoadingAndRetryManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: 30453
 * Date: 2016/12/24 11:45
 */
public class UserFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {

    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.titleLayout) TitleLayout titleLayout;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;
    private TextView tvCount;
    private UserListAdapter adapter;
    private LoadingAndRetryManager layoutManager;
    private String type;
    private String typeName = "全部用户";
    private String[] userTypeName;
    private String[] userType;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView() {
        layoutManager = bindRefreshView(ptrFrameLayout, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        adapter = new UserListAdapter(Collections.emptyList());
        adapter.openLoadMore(PAGE_SIZE, true);
        recyclerView.setAdapter(adapter);
        View view = View.inflate(getActivity(), R.layout.head_count_layout, null);
        tvCount = (TextView) view.findViewById(R.id.tv_count);
        adapter.addHeaderView(view);
        userTypeName = getResources().getStringArray(R.array.user_type_name);
        userType = getResources().getStringArray(R.array.user_type);
    }

    @Override
    public void setListener() {
        titleLayout.setOnRightListener(v -> rightListener());
        adapter.setOnLoadMoreListener(this);
        adapter.setOnRecyclerViewItemClickListener((view, i) -> {
            UserEntity item = adapter.getItem(i);
            UserDetailActivity.start(getActivity(), item);
        });
    }

    private void rightListener() {
        new CustomPopWindow(getActivity(), Arrays.asList(userTypeName))
                .setOnItemClickListener((view, position) -> {
                    if (Objects.equals(type, userType[position])) {
                        return;
                    }
                    type = userType[position];
                    typeName = userTypeName[position];
                    initData(LoadStatus.LOADING);
                }).show(titleLayout.getRightView(), 0, 10);
    }

    @Override
    public void initData(LoadStatus status) {
        String accessToken = SpHelper.getAccessToken();
        CURRENT_PAGE = status == LoadStatus.MORE ? CURRENT_PAGE : 1;
        HttpModule.newHttpApi().getUserList(HttpParams.getUserList(accessToken, type, String.valueOf(CURRENT_PAGE), String.valueOf(PAGE_SIZE)))
                .compose(RxUtils.rxSchedulerHelper())
                .flatMap(new BaseRespFunc<>())
                .subscribe(new BaseSubscriber<Users>(layoutManager, status) {
                    @Override
                    public void onNext(Users users) {
                        tvCount.setText(getString(R.string.user_count,typeName,users.getUserCount()));
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
}
