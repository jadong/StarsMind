package com.dong.starsmind.weather.presenter;

import android.util.Log;

import com.dong.starsmind.api.WeatherApi;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by zengwendong on 16/11/18.
 */
public class WeatherPresenter {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.weather.com.cn/")
            .build();

    public void getWeather() {
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<ResponseBody> weather = weatherApi.getWeather();
        weather.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.i("WeatherPresenter", "---->" + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("WeatherPresenter", "---->" + t.getMessage());
            }
        });

    }
}
