package com.iiseeuu.helper.http;

import com.iiseeuu.helper.entity.BaseResp;

import rx.Observable;
import rx.functions.Func1;

/**
 * Author: 30453
 * Date: 2016/12/23 15:26
 */
public class BaseRespFunc<T> implements Func1<BaseResp<T>, Observable<T>> {
    @Override
    public Observable<T> call(BaseResp<T> resp) {
        if (Integer.parseInt(resp.getStatusCode()) != 200) {
            return Observable.error(new Exception());
        }
        return Observable.just(resp.getData());
    }
}
