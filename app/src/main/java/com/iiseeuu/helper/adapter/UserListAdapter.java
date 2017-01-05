package com.iiseeuu.helper.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.entity.UserEntity;
import com.iiseeuu.helper.utils.TimeUtils;

import java.util.List;

/**
 * Author: 30453
 * Date: 2017/1/3 11:14
 */
public class UserListAdapter extends BaseQuickAdapter<UserEntity> {

    public UserListAdapter(List<UserEntity> data) {
        super(R.layout.item_user_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, UserEntity params) {
        holder.setText(R.id.tv_name, params.getNickname());
        holder.setText(R.id.tv_printCount, params.getPrints_count());
        holder.setText(R.id.tv_createdAt, TimeUtils.dateToString(params.getCreated_at()));
        holder.getView(R.id.tv_owner).setVisibility(params.is_owner() ? View.VISIBLE : View.INVISIBLE);
        ImageView avatar = holder.getView(R.id.avatar);
        Glide.with(mContext).load(params.getHeadimgurl())
                .asBitmap()
                .thumbnail(0.3f)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(R.drawable.def_avatar)
                .placeholder(R.drawable.def_avatar)
                .into(avatar);
    }
}
