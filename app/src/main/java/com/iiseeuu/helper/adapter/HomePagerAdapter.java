package com.iiseeuu.helper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.iiseeuu.helper.fragment.HomeFragment;
import com.iiseeuu.helper.fragment.OrdersFragment;
import com.iiseeuu.helper.fragment.PrintFragment;
import com.iiseeuu.helper.fragment.UserFragment;

/**
 * Author: 30453
 * Date: 2016/12/23 18:05
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    public SparseArray<Fragment> fragments;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    private Fragment getFragment(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new OrdersFragment();
                    break;
                case 2:
                    fragment = new UserFragment();
                    break;
                case 3:
                    fragment = new PrintFragment();
                    break;
            }
            fragments.put(position, fragment);
        }
        return fragment;
    }
}
