package com.iiseeuu.helper.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.adapter.OrdersAdapter;
import com.iiseeuu.helper.base.BaseFragment;
import com.iiseeuu.helper.entity.OrdersEntity;
import com.iiseeuu.helper.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: 30453
 * Date: 2016/12/24 11:45
 */
public class OrdersFragment extends BaseFragment {


    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;
    private OrdersAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_orders;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        adapter = new OrdersAdapter(Collections.emptyList());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        adapter.setNewData(getData());
    }

    private List<OrdersEntity> getData() {
        List<OrdersEntity> list = new ArrayList<>();
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        list.add(new OrdersEntity());
        return list;
    }
}
