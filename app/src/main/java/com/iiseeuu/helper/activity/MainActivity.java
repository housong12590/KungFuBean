package com.iiseeuu.helper.activity;

import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.adapter.MainPagerAdapter;
import com.iiseeuu.helper.base.BaseActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.viewPager) ViewPager viewPager;
    @Bind(R.id.rb_home) RadioButton rbHome;
    @Bind(R.id.radioGroup) RadioGroup radioGroup;
    private long exitTime = 0;
    private MainPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        rbHome.setChecked(true);
        radioGroup.setOnCheckedChangeListener(this);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.rb_orders:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.rb_user:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.rb_print:
                viewPager.setCurrentItem(3, false);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
