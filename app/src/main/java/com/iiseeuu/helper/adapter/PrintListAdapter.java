package com.iiseeuu.helper.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iiseeuu.helper.BaseApplication;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.entity.PrintEntity;

import java.util.List;

/**
 * Author: 30453
 * Date: 2017/1/3 16:09
 */
public class PrintListAdapter extends BaseQuickAdapter<PrintEntity> {

    public PrintListAdapter(List<PrintEntity> data) {
        super(R.layout.item_print_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, PrintEntity params) {
        holder.setText(R.id.tv_alias, params.getAlias());
        holder.setText(R.id.tv_nickname, params.getNickname());
        holder.setText(R.id.tv_type, params.getName());
        //        holder.setText(R.id.tv_totalCount, params.getTotalPrintCount());
        //        holder.setText(R.id.tv_todayCount, params.getTodayPrintCount());

        ImageView image = holder.getView(R.id.image);
//        image.setColorFilter(params.isOnline() ? android.R.color.transparent : Color.parseColor("#88000000"));
        Glide.with(mContext).load(BaseApplication.getPrintIcon().get(getSubType(params.getName())))
                .asBitmap()
                .placeholder(R.drawable.def_icon)
                .error(R.drawable.def_icon)
                .into(image);
    }

    private String getSubType(String type) {
        if (TextUtils.isEmpty(type)) {
            return "";
        }
        int index = type.indexOf("L");
        return type.substring(index, index + 4);
    }
}
