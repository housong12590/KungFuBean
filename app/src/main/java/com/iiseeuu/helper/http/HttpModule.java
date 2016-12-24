package com.iiseeuu.helper.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: 30453
 * Date: 2016/12/23 15:16
 */
public class HttpModule {

    private static final String BASE_URL = "";

    public static ApiService newHttpApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .build();
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }
}
