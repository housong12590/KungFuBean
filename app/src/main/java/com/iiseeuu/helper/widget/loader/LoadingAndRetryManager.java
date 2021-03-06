package com.iiseeuu.helper.widget.loader;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.iiseeuu.helper.R;

/**
 * Created by zhy on 15/8/27.
 */
public class LoadingAndRetryManager {
    public static final int NO_LAYOUT_ID = 0;
    public int BASE_LOADING_LAYOUT_ID = R.layout.loading_base_layout;
    public int BASE_RETRY_LAYOUT_ID = R.layout.loader_retry_layout;
    public int BASE_EMPTY_LAYOUT_ID = R.layout.loader_base_empty;

    public static final int TYPE_EMPTY_NODATA = 1000;
    public static final int TYPE_EMPTY_NOFAVORITE = 1001;
    public static final int TYPE_EMPTY_NOPUBLISH = 1002;
    public static final int TYPE_EMPTY_NOMESSAGE = 1003;
    public static final int TYPE_EMPTY_RECEIVE = 1004;
    public static final int TYPE_DEFAULT = 999;

    public LoadingAndRetryLayout mLoadingAndRetryLayout;


    public OnLoadingAndRetryListener DEFAULT_LISTENER = new OnLoadingAndRetryListener() {
        @Override
        public void setRetryEvent(View retryView) {

        }
    };


    public LoadingAndRetryManager(Object activityOrFragmentOrView, OnLoadingAndRetryListener listener) {
        if (listener == null)
            listener = DEFAULT_LISTENER;

        ViewGroup contentParent = null;
        Context context;
        if (activityOrFragmentOrView instanceof Activity) {
            Activity activity = (Activity) activityOrFragmentOrView;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (activityOrFragmentOrView instanceof Fragment) {
            Fragment fragment = (Fragment) activityOrFragmentOrView;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());
        } else if (activityOrFragmentOrView instanceof View) {
            View view = (View) activityOrFragmentOrView;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)");
        }
        int childCount = contentParent.getChildCount();
        //get contentParent
        int index = 0;
        View oldContent;
        if (activityOrFragmentOrView instanceof View) {
            oldContent = (View) activityOrFragmentOrView;
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    index = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0);
        }
        contentParent.removeView(oldContent);
        //setup content layout
        LoadingAndRetryLayout loadingAndRetryLayout = new LoadingAndRetryLayout(context);

        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        contentParent.addView(loadingAndRetryLayout, index, lp);
        loadingAndRetryLayout.setContentView(oldContent);
        // setup loading,retry,empty layout
        setupRetryLayout(listener, loadingAndRetryLayout);
        setupEmptyLayout(listener, loadingAndRetryLayout);
        setupLoadingLayout(listener, loadingAndRetryLayout);
        //callback
        listener.setRetryEvent(loadingAndRetryLayout.getRetryView());
        listener.setLoadingEvent(loadingAndRetryLayout.getLoadingView());
        listener.setEmptyEvent(loadingAndRetryLayout.getEmptyView());
        mLoadingAndRetryLayout = loadingAndRetryLayout;
    }

    private void setupEmptyLayout(OnLoadingAndRetryListener listener, LoadingAndRetryLayout loadingAndRetryLayout) {
        if (listener.isSetEmptyLayout()) {
            int layoutId = listener.generateEmptyLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                loadingAndRetryLayout.setEmptyView(layoutId);
            } else {
                loadingAndRetryLayout.setEmptyView(listener.generateEmptyLayout());
            }
        } else {
            if (BASE_EMPTY_LAYOUT_ID != NO_LAYOUT_ID)
                loadingAndRetryLayout.setEmptyView(BASE_EMPTY_LAYOUT_ID);
        }
    }

    private void setupLoadingLayout(OnLoadingAndRetryListener listener, LoadingAndRetryLayout loadingAndRetryLayout) {
        if (listener.isSetLoadingLayout()) {
            int layoutId = listener.generateLoadingLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                loadingAndRetryLayout.setLoadingView(layoutId);
            } else {
                loadingAndRetryLayout.setLoadingView(listener.generateLoadingLayout());
            }
        } else {
            if (BASE_LOADING_LAYOUT_ID != NO_LAYOUT_ID)
                loadingAndRetryLayout.setLoadingView(BASE_LOADING_LAYOUT_ID);
        }
    }

    private void setupRetryLayout(OnLoadingAndRetryListener listener, LoadingAndRetryLayout loadingAndRetryLayout) {
        if (listener.isSetRetryLayout()) {
            int layoutId = listener.generateRetryLayoutId();
            if (layoutId != NO_LAYOUT_ID) {
                loadingAndRetryLayout.setLoadingView(layoutId);
            } else {
                loadingAndRetryLayout.setLoadingView(listener.generateRetryLayout());
            }
        } else {
            if (BASE_RETRY_LAYOUT_ID != NO_LAYOUT_ID)
                loadingAndRetryLayout.setRetryView(BASE_RETRY_LAYOUT_ID);
        }
    }

    public static LoadingAndRetryManager generate(Object activityOrFragment, OnLoadingAndRetryListener listener) {
        return new LoadingAndRetryManager(activityOrFragment, listener);
    }

    public View getContentView() {
        return mLoadingAndRetryLayout.getContentView();
    }

    public void showLoading() {
        mLoadingAndRetryLayout.showLoading();
    }

    public void showRetry() {
        mLoadingAndRetryLayout.showRetry();
    }

    public void showContent() {
        mLoadingAndRetryLayout.showContent();
    }

    /**
     * 空白界面类型
     */
    public void showEmpty() {
        mLoadingAndRetryLayout.showEmpty(0);
    }


}
