package com.iiseeuu.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseActivity;

import java.io.File;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoView;

public class ImagePreviewActivity extends BaseActivity {

    @Bind(R.id.iv_image) PhotoView ivImage;
    @Bind(R.id.toolBar) Toolbar toolbar;
    private String path;

    public static void start(Context context, String path) {
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        intent.putExtra("path", path);
        context.startActivity(intent);
    }

    @Override
    public void parseIntent(Intent intent) {
        path = intent.getStringExtra("path");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    public void initView() {
        toolbar.setTitle("图片预览");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    public void initData() {
        if (path.contains("http://") || path.contains("https://")) {
            Glide.with(this).load(path).placeholder(R.drawable.def_icon).into(ivImage);
        } else {
            Glide.with(this).load(new File(path)).placeholder(R.drawable.def_icon).into(ivImage);
        }
    }
}
