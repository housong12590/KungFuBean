package com.iiseeuu.helper.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseActivity;
import com.iiseeuu.helper.entity.Login;
import com.iiseeuu.helper.http.BaseRespFunc;
import com.iiseeuu.helper.http.BaseSubscriber;
import com.iiseeuu.helper.http.HttpModule;
import com.iiseeuu.helper.utils.RxUtils;
import com.iiseeuu.helper.utils.SpHelper;
import com.iiseeuu.helper.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.toolBar) Toolbar toolBar;
    @Bind(R.id.et_userName) EditText etUserName;
    @Bind(R.id.et_password) EditText etPassword;
    @Bind(R.id.autoLogin) CheckBox rbAutoLogin;
    @Bind(R.id.rememberPassword) CheckBox rbRememberPassword;

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
        etUserName.setText(SpHelper.getUserName());
        etPassword.setText(SpHelper.getPassword());
        rbAutoLogin.setChecked(SpHelper.isAutoLogin());
        rbRememberPassword.setChecked(SpHelper.isRememberPassword());
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
                .subscribe(new BaseSubscriber<Login>(this) {
                    @Override
                    public void onNext(Login login) {
                        SpHelper.setAccessToken(login.getAccessToken());
                        saveParams(userName, password);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }

    private void saveParams(String userName, String password) {
        boolean rememberPassword = rbRememberPassword.isChecked();
        boolean autoLogin = rbAutoLogin.isChecked();
        SpHelper.setUserName(userName);
        SpHelper.setPassword(rememberPassword ? password : "");
        SpHelper.setRememberPassword(rememberPassword);
        SpHelper.setAutoLogin(autoLogin);
    }

    @OnClick(R.id.tv_login)
    public void onClick(View view) {
        login();
    }
}
