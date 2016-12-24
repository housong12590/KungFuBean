package com.iiseeuu.helper.http;

import com.iiseeuu.helper.entity.BaseResp;
import com.iiseeuu.helper.entity.Login;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author: 30453
 * Date: 2016/12/23 15:20
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("user/users/login")
    Observable<BaseResp<Login>> login(@Field("userName") String username, @Field("password") String password);

}
