package com.iiseeuu.helper.http;

import com.iiseeuu.helper.entity.AuthUser;
import com.iiseeuu.helper.entity.BaseResp;
import com.iiseeuu.helper.entity.DataItem;
import com.iiseeuu.helper.entity.DataStats;
import com.iiseeuu.helper.entity.Login;
import com.iiseeuu.helper.entity.Orders;
import com.iiseeuu.helper.entity.PrintEntity;
import com.iiseeuu.helper.entity.Prints;
import com.iiseeuu.helper.entity.UserEntity;
import com.iiseeuu.helper.entity.Users;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Author: 30453
 * Date: 2016/12/23 15:20
 */
public interface ApiService {

    //登录
    @FormUrlEncoded
    @POST("admin/login")
    Observable<BaseResp<Login>> login(@Field("username") String username, @Field("password") String password);

    //获取主页数据
    @GET("data_stats")
    Observable<BaseResp<DataStats>> getDataStats(@Query("access_token") String token);

    //获取趋势数据
    @GET("data_stats/trend")
    Observable<BaseResp<List<DataItem>>> getTendencyData(@Query("access_token") String token, @Query("trend_type") String type, @Query("start_date") String startTime, @Query("end_date") String endTime);

    //获取全部订单列表
    @GET("designs")
    Observable<BaseResp<Orders>> getAllOrderList(@Query("access_token") String accessToken, @Query("page") String page, @Query("page_size") String pageSize);

    //获取订单列表
    @GET("person/{id}/designs")
    Observable<BaseResp<Orders>> getUserOrderList(@Path("id") String id, @Query("access_token") String accessToken, @Query("page") String page, @Query("page_size") String pageSize);

    //获取订单列表
    @GET("print/{id}/designs")
    Observable<BaseResp<Orders>> getPrintOrderList(@Path("id") String id, @Query("access_token") String accessToken, @Query("page") String page, @Query("page_size") String pageSize);

    //获取用户列表
    @GET("persons")
    Observable<BaseResp<Users>> getUserList(@QueryMap Map<String, String> params);

    //获取用户详情
    @GET("person/{id}")
    Observable<BaseResp<UserEntity>> getUserDetail(@Path("id") String id, @Query("access_token") String token);

    //获取打印机列表
    @GET("prints")
    Observable<BaseResp<Prints>> getPrintList(@Query("access_token") String token, @Query("page") String page, @Query("page_size") String pageSize);

    //打印机详情
    @GET("")
    Observable<BaseResp<PrintEntity>> getPrintDetail(@Query("access_token") String token, @Query("id") String id);

    //获取授权用户
    @GET("")
    Observable<BaseResp<AuthUser>> getAuthUserList(@Query("access_token") String token, @Query("id") String id, @Query("page") String page, @Query("page_size") String pageSize);
}
