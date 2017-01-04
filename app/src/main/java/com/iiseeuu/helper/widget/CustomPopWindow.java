package com.iiseeuu.helper.widget;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.iiseeuu.helper.R;

import java.util.List;

/**
 * Author: 30453
 * Date: 2017/1/4 15:51
 */
public class CustomPopWindow extends PopupWindow {

    private OnItemClickListener listener;
    private Activity activity;

    public CustomPopWindow(Activity activity, List<String> list) {
        this.activity = activity;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        View view = View.inflate(activity, R.layout.popup_layout, null);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, list));
        setContentView(view);
        setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.pop_bg));
        setTouchable(true);
        setFocusable(true);
        setWidth(width / 3);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOnDismissListener(() -> backgroundAlpha(activity, 1.0f));

        listView.setOnItemClickListener((parent, v, position, id) -> {
            if (listener != null) {
                listener.itemClick(v, position);
            }
            dismiss();
        });
    }

    public CustomPopWindow setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
        return this;
    }


    public CustomPopWindow show(View view, int xoff, int yoff) {
        showAsDropDown(view, xoff, yoff);
        if (isShowing()) {
            backgroundAlpha(activity, 0.8f);
        }
        return this;
    }


    public void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

}
