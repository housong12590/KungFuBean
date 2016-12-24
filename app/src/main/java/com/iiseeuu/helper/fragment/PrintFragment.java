package com.iiseeuu.helper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseFragment;

/**
 * Author: 30453
 * Date: 2016/12/24 11:46
 */
public class PrintFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_print,null);
        return view;
    }
}
