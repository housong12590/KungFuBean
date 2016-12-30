package com.iiseeuu.helper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.entity.OrdersEntity;

import java.util.List;

/**
 * Author: 30453
 * Date: 2016/12/28 11:14
 */
public class OrdersAdapter extends BaseQuickAdapter<OrdersEntity> {

    public OrdersAdapter(List<OrdersEntity> data) {
        super(R.layout.item_orders_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, OrdersEntity params) {
//        holder.setText(R.id.tv_name, params.getUser().getName());
//        holder.setText(R.id.tv_time, params.getPrintTime());
//        holder.setText(R.id.tv_city, params.getCreateCity());
//        holder.setText(R.id.tv_type, params.getFacility().getType());
//        Glide.with(mContext).load(params.getImageUrl()).into((ImageView) holder.getView(R.id.image));
    }
}
