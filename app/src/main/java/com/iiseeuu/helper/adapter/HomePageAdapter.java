package com.iiseeuu.helper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.entity.Num;

import java.util.List;

/**
 * Author: 30453
 * Date: 2016/12/28 09:52
 */
public class HomePageAdapter extends BaseQuickAdapter<Num> {
    public HomePageAdapter(List<Num> data) {
        super(R.layout.item_home_page, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Num num) {
        holder.setText(R.id.tv_name, num.getName());
        holder.setText(R.id.tv_count, num.getCount());
        holder.setText(R.id.tv_new_count, num.getNewCount());
    }
}
