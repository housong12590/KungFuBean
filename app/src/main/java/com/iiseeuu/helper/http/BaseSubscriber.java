package com.iiseeuu.helper.http;

import android.content.Context;
import android.os.Looper;

import com.iiseeuu.helper.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Author: 30453
 * Date: 2016/12/23 15:50
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private ProgressDialogHandler dialog;
    private LoadStatus status;

    public BaseSubscriber(Context context) {
        if (context != null) {
            dialog = new ProgressDialogHandler(context, this, true);
        }
    }

    public BaseSubscriber() {

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
            showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
            ToastUtils.show("网络连接超时");
        }
        dismissProgressDialog();
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
