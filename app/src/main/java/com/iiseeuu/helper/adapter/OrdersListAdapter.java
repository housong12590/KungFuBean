package com.iiseeuu.helper.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.entity.OrdersEntity;
import com.iiseeuu.helper.utils.TimeUtils;

import java.util.List;

/**
 * Author: 30453
 * Date: 2016/12/28 11:14
 */
public class OrdersListAdapter extends BaseMultiItemQuickAdapter<OrdersEntity> {

    public static final int NORMAL_ITEM = 0;
    public static final int EMPTY_ITEM = 1;

    public OrdersListAdapter(List<OrdersEntity> data) {
        super(data);
        addItemType(NORMAL_ITEM, R.layout.item_orders_layout);
        addItemType(EMPTY_ITEM, R.layout.item_empty_layout);
    }


    @Override
    protected void convert(BaseViewHolder holder, OrdersEntity params) {
        switch (holder.getItemViewType()) {
            case NORMAL_ITEM:
                holder.setText(R.id.tv_name, params.getNickname());
                holder.setText(R.id.tv_time, TimeUtils.dateToString(params.getCreated_at()));
                holder.setText(R.id.tv_city, params.getCity());
                holder.setText(R.id.tv_type, params.getPrintName());
                Glide.with(mContext).load(params.getFinishUrl())
                        .asBitmap()
                        .thumbnail(0.3f)
                        .priority(Priority.HIGH)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT )
                        .placeholder(R.drawable.def_icon)
                        .error(R.drawable.def_icon)
                        .into((ImageView) holder.getView(R.id.image));
                break;
            case EMPTY_ITEM:

                break;
        }

    }
}
