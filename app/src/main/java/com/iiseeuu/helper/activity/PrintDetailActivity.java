package com.iiseeuu.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseActivity;
import com.iiseeuu.helper.entity.PrintEntity;

import butterknife.Bind;

public class PrintDetailActivity extends BaseActivity {

    @Bind(R.id.toolBar) Toolbar toolbar;
    private PrintEntity entity;

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
    }

    @Override
    public void initData() {

    }
}
