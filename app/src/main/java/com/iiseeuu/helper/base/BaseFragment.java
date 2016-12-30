package com.iiseeuu.helper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Author: 30453
 * Date: 2016/12/23 10:15
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public void initData(){

    }

//    public LoadingAndRetryManager bindRefreshView(Object obj, OnRefreshListener listener) {
//        if (obj instanceof PtrFrameLayout) {
//            PtrFrameLayout ptr = (PtrFrameLayout) obj;
//            PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getActivity());
//            ptr.setHeaderView(header);
//            ptr.addPtrUIHandler(header);
//            ptr.setPtrHandler(new PtrHandler() {
//                @Override
//                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//                }
//
//                @Override
//                public void onRefreshBegin(PtrFrameLayout frame) {
//                    if (listener != null) {
//                        listener.onRefresh();
//                    }
//                }
//            });
//        }
//        return new LoadingAndRetryManager(obj, new OnLoadingAndRetryListener() {
//            @Override
//            public void setRetryEvent(View retryView) {
//                retryView.findViewById(R.id.id_btn_retry).setOnClickListener(v -> {
//                    if (listener != null) {
//                        listener.onRetry();
//                    }
//                });
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
