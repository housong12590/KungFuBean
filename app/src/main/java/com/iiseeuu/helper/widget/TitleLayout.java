package com.iiseeuu.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iiseeuu.helper.R;


/**
 * Author: 30453
 * Date: 2016/10/26 10:25
 */
public class TitleLayout extends RelativeLayout {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageView ivRight;

    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        String title = ta.getString(R.styleable.TitleLayout_tl_title);
        String right = ta.getString(R.styleable.TitleLayout_tl_right);
        int leftImageId = ta.getResourceId(R.styleable.TitleLayout_tl_left_image, 0);
        int rightImageId = ta.getResourceId(R.styleable.TitleLayout_tl_right_image, 0);
        ta.recycle();
        inflate(getContext(), R.layout.base_title_layout, this);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        if (title != null) {
            tvTitle.setText(title);
        }
        if (leftImageId != 0) {
            ivBack.setImageResource(leftImageId);
        }
        if (rightImageId != 0) {
            ivRight.setImageResource(rightImageId);
        }
        if (right != null) {
            tvRight.setText(right);
        }
    }

    public void setTitle(String text) {
        tvTitle.setText(text);
    }

    public void setBackImage(int resId) {
        ivBack.setImageResource(resId);
    }

    public void setRightText(String text) {
        tvRight.setText(text);
    }

    public void setRightImage(int resId) {
        ivRight.setImageResource(resId);
    }

    public View getRightView() {
        return tvRight;
    }

    public LinearLayout setOnBackClickListener(OnClickListener listener) {
        if (ivBack != null && listener != null) {
            ivBack.setOnClickListener(listener);
        }
        return null;
    }

    public void setOnRightListener(OnClickListener listener) {
        if (TextUtils.isEmpty(tvRight.getText().toString())) {
            ivRight.setOnClickListener(listener);
        } else {
            tvRight.setOnClickListener(listener);
        }

    }
}
