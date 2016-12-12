package com.dong.starsmind.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by zengwendong on 16/11/18.
 */
public interface WeatherApi {

    /**
     * http://www.weather.com.cn/data/sk/101010100.html
     http://www.weather.com.cn/data/cityinfo/101010100.html
     http://m.weather.com.cn/data/101010100.html
     * @return
     */
    @POST("data/sk/101010100.html")
    Call<ResponseBody> getWeather();

}
