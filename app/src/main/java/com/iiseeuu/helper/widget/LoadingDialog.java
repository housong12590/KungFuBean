package com.iiseeuu.helper.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.iiseeuu.helper.R;

/**
 * Author: 30453
 * Date: 2016/12/23 15:52
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.base_loading_layout, null);
        setCanceledOnTouchOutside(false);
        setContentView(view);
    }
}
