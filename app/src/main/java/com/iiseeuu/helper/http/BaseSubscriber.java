package com.iiseeuu.helper.http;

import android.content.Context;
import android.os.Looper;
import android.view.View;

import com.iiseeuu.helper.utils.ToastUtils;
import com.iiseeuu.helper.widget.loader.LoadingAndRetryManager;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Subscriber;

/**
 * Author: 30453
 * Date: 2016/12/23 15:50
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    protected LoadingAndRetryManager lm = null;
    private PtrFrameLayout ptrFrameLayout;
    private ProgressDialogHandler dialog;
    private LoadStatus status;

    public BaseSubscriber(Context context) {
        if (context != null) {
            dialog = new ProgressDialogHandler(context, this, true);
        }
    }

    public BaseSubscriber() {

    }

    public BaseSubscriber(LoadingAndRetryManager val, LoadStatus state) {
        View view = val.getContentView();
        if (view instanceof PtrFrameLayout) {
            ptrFrameLayout = (PtrFrameLayout) view;
        }
        lm = val;
        this.status = state;
    }

    private void showProgressDialog() {
        if (dialog != null) {
            dialog.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (dialog != null) {
            dialog.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            dialog = null;
        }
    }


    @Override
    public void onStart() {
        if (isMainThread()) {
            if (lm != null && status == LoadStatus.LOADING) {
                lm.showLoading();
            }
            showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
        if (ptrFrameLayout != null) {
            ptrFrameLayout.refreshComplete();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        dismissProgressDialog();
        if (lm != null && status == LoadStatus.LOADING) {
            lm.showRetry();
        }
        if (dialog != null) {
            dismissProgressDialog();
        }
        if (ptrFrameLayout != null) {
            ptrFrameLayout.refreshComplete();
        }
        if (e instanceof SocketTimeoutException || e instanceof ConnectException || e instanceof UnknownHostException) {
            ToastUtils.show("网络连接异常");
        } else {
            ToastUtils.show(e.getMessage());
        }
    }

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @Override
    public void onCancelProgress() {
        if (!isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
