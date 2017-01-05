package com.iiseeuu.helper.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.entity.UserEntity;

import java.util.List;

/**
 * Author: 30453
 * Date: 2017/1/5 16:45
 */
public class AccreditUserAdapter extends BaseQuickAdapter<UserEntity> {

    public AccreditUserAdapter(List<UserEntity> data) {
        super(R.layout.item_accredit_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, UserEntity params) {
        holder.setText(R.id.tv_name,params.getNickname());
        Glide.with(mContext).load(params.getHeadimgurl()).asBitmap()
                .placeholder(R.drawable.def_avatar)
                .error(R.drawable.def_avatar)
                .into((ImageView) holder.getView(R.id.avatar));
    }
}
