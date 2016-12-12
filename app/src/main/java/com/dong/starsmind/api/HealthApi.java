package com.dong.starsmind.api;


import com.dong.starsmind.constant.AppConstants;
import com.dong.starsmind.news.entity.ResponseEntity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by zengwendong on 16/11/21.
 */
public interface HealthApi {

    @Headers(AppConstants.baidu_apikey)
    @GET("txapi/health/health")
    Call<ResponseEntity> getMeiNvImages(@QueryMap HashMap<String, String> map);

}
