package com.dong.starsmind.api;

import com.dong.starsmind.tuling.entity.TuLingData;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by zengwendong on 16/11/24.
 */
public interface TuLingApi {

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("openapi/api")
    Call<TuLingData> sendMessage(@Body RequestBody requestBody);

}
