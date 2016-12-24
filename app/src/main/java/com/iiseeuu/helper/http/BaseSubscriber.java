package com.iiseeuu.helper.http;

import android.content.Context;
import android.os.Looper;

import rx.Subscriber;

/**
 * Author: 30453
 * Date: 2016/12/23 15:50
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private ProgressDialogHandler dialog;

    public BaseSubscriber(Context context) {
        if (context != null) {
            dialog = new ProgressDialogHandler(context, this, true);
        }
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
