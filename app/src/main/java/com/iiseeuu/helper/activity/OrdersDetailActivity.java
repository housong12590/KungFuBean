package com.iiseeuu.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseActivity;

import butterknife.Bind;

public class OrdersDetailActivity extends BaseActivity {

    @Bind(R.id.toolBar) Toolbar toolbar;
    private String id;

    public static void start(Context context,String id){
        Intent intent = new Intent(context, OrdersDetailActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    public void parseIntent(Intent intent) {
        id = intent.getStringExtra("id");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_orders_detail;
    }

    @Override
    public void initView() {
        toolbar.setTitle("订单详情");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    public void initData() {

    }
}
