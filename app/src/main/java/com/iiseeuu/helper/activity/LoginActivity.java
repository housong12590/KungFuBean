package com.iiseeuu.helper.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseActivity;
import com.iiseeuu.helper.http.BaseRespFunc;
import com.iiseeuu.helper.http.HttpModule;
import com.iiseeuu.helper.utils.RxUtils;
import com.iiseeuu.helper.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.toolBar) Toolbar toolBar;
    @Bind(R.id.et_userName) EditText etUserName;
    @Bind(R.id.et_password) EditText etPassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        toolBar.setTitle("登录");
        toolBar.setNavigationIcon(R.drawable.back_icon);
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    public void initData() {

    }

    private void login() {
        String password = etPassword.getText().toString();
        String userName = etUserName.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.show("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("密码不能为空");
            return;
        }
        HttpModule.newHttpApi().login(userName, password).flatMap(new BaseRespFunc<>())
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(login -> finish());
    }

    @OnClick(R.id.tv_login)
    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
