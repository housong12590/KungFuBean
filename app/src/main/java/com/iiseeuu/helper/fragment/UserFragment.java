package com.iiseeuu.helper.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseFragment;
import com.iiseeuu.helper.widget.DividerItemDecoration;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author: 30453
 * Date: 2016/12/24 11:45
 */
public class UserFragment extends BaseFragment {

    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.ptrFrameLayout) PtrFrameLayout ptrFrameLayout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.setAdapter(null);
    }

    @Override
    public void initData() {
//        HttpModule.newHttpApi().getUserList(SpHelper.getAccessToken(), "", "", "").compose(RxUtils.rxSchedulerHelper())
//                .flatMap(new BaseRespFunc<>()).subscribe(new BaseSubscriber<Users>(getActivity()) {
//            @Override
//            public void onNext(Users users) {
//                ptrFrameLayout.refreshComplete();
//            }
//        });
    }
}
