package com.iiseeuu.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.adapter.OrdersListAdapter;
import com.iiseeuu.helper.base.BaseActivity;
import com.iiseeuu.helper.entity.Orders;
import com.iiseeuu.helper.entity.OrdersEntity;
import com.iiseeuu.helper.entity.UserEntity;
import com.iiseeuu.helper.http.BaseRespFunc;
import com.iiseeuu.helper.http.BaseSubscriber;
import com.iiseeuu.helper.http.HttpModule;
import com.iiseeuu.helper.http.LoadStatus;
import com.iiseeuu.helper.utils.RxUtils;
import com.iiseeuu.helper.utils.SpHelper;
import com.iiseeuu.helper.utils.TimeUtils;
import com.iiseeuu.helper.widget.DividerItemDecoration;
import com.iiseeuu.helper.widget.OnRefreshListener;
import com.iiseeuu.helper.widget.loader.LoadingAndRetryManager;

import java.util.Collections;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class UserDetailActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {

    @Bind(R.id.avatar) ImageView avatar;
    @Bind(R.id.tv_name) TextView tvName;
    @Bind(R.id.tv_sex) TextView tvSex;
    @Bind(R.id.tv_owner) TextView tvOwner;
    @Bind(R.id.tv_printCount) TextView tvPrintCount;
    @Bind(R.id.tv_printTime) TextView tvPrintTime;
    @Bind(R.id.tv_subscribe) TextView tvSubscribe;
    @Bind(R.id.toolBar) Toolbar toolbar;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;
    private OrdersListAdapter adapter;
    private UserEntity userEntity;
    private String userId;
    private String accessToken;
    private LoadingAndRetryManager layoutManager;

    public static void start(Context context, UserEntity entity) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra("data", entity);
        context.startActivity(intent);
    }

    @Override
    public void parseIntent(Intent intent) {
        userEntity = (UserEntity) intent.getSerializableExtra("data");
        userId = userEntity.getId();
        accessToken = SpHelper.getAccessToken();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void initView() {
        toolbar.setTitle("用户详情");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> finish());
        layoutManager = bindRefreshView(ptrFrameLayout, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        adapter = new OrdersListAdapter(Collections.emptyList());
        adapter.openLoadMore(PAGE_SIZE, true);
        recyclerView.setAdapter(adapter);
        setRecyclerViewHeadViewData();
    }

    @Override
    public void setListener() {
        adapter.setOnLoadMoreListener(this);
    }

    @Override
    public void initData() {
        HttpModule.newHttpApi().getUserDetail(userId, accessToken).compose(RxUtils.rxSchedulerHelper())
                .flatMap(new BaseRespFunc<>())
                .subscribe(new BaseSubscriber<UserEntity>() {
                    @Override
                    public void onNext(UserEntity userEntity) {
                        UserDetailActivity.this.userEntity = userEntity;
                        setRecyclerViewHeadViewData();
                    }
                });

    }

    @Override
    public void initDataFromNet(LoadStatus status) {
        CURRENT_PAGE = status == LoadStatus.MORE ? CURRENT_PAGE : 1;
        HttpModule.newHttpApi().getUserOrderList(userId, accessToken, String.valueOf(CURRENT_PAGE), String.valueOf(PAGE_SIZE))
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

    private void setRecyclerViewHeadViewData() {
        if (userEntity == null) {
            return;
        }
        Glide.with(this).load(userEntity.getHeadimgurl()).asBitmap()
                .placeholder(R.drawable.def_avatar)
                .error(R.drawable.def_avatar)
                .into(avatar);
        tvSex.setText(userEntity.getSex());
        tvName.setText(userEntity.getNickname());
        tvPrintCount.setText(userEntity.getPrints_count());
        tvPrintTime.setText(TimeUtils.dateToString(userEntity.getRecently_print_time()));
        tvSubscribe.setText(userEntity.isSubscribe() ? "已关注" : "未关注");
        tvOwner.setVisibility(userEntity.is_owner() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onLoadMoreRequested() {
        CURRENT_PAGE++;
        initDataFromNet(LoadStatus.MORE);
    }

    @Override
    public void onRefresh() {
        initData();
        initDataFromNet(LoadStatus.REFRESH);
    }

    @Override
    public void onRetry() {
        initDataFromNet(LoadStatus.LOADING);
    }
}
