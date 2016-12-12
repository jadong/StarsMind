package com.dong.starsmind.joke.presenter;

import android.text.TextUtils;

import com.dong.starsmind.api.JokeApi;
import com.dong.starsmind.app.SMLog;
import com.dong.starsmind.common.entity.JuHeResponse;
import com.dong.starsmind.joke.entity.Joke;
import com.dong.starsmind.news.view.JokeListView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zengwendong on 16/12/5.
 */
public class JokePresenter {

    private final String key = "55368dcf90e843e02cddceb769f1c5fe";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://japi.juhe.cn/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private JokeListView jokeListView;

    public JokePresenter(JokeListView jokeListView) {
        this.jokeListView = jokeListView;
    }

    /**
     * 最新笑话
     */
    public void getJokeList(String type, int page) {
        JokeApi jokeApi = retrofit.create(JokeApi.class);
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("pagesize", 20);
        params.put("key", key);

        Call<JuHeResponse<Joke>> call;
        if (TextUtils.equals(type, "image")) {
            params.put("pagesize", 10);
            call = jokeApi.getJokeImageList(params);
        } else {
            call = jokeApi.getJokeList(params);
        }
        call.enqueue(new Callback<JuHeResponse<Joke>>() {
            @Override
            public void onResponse(Call<JuHeResponse<Joke>> call, Response<JuHeResponse<Joke>> response) {
                if (jokeListView == null) {
                    return;
                }

                JuHeResponse<Joke> body = response.body();
                if (body.getError_code() == 0) {
                    jokeListView.refreshData(false, body.getResult().getData());
                } else {
                    jokeListView.showError(body.getReason());
                }

            }

            @Override
            public void onFailure(Call<JuHeResponse<Joke>> call, Throwable t) {
                SMLog.e("JokePresenter", "onFailure--->", t);
                jokeListView.showError("请求异常");
            }
        });
    }

    /**
     * 按时间查询笑话
     */
    public void getJokeByTime(String type, long time) {
        JokeApi jokeApi = retrofit.create(JokeApi.class);
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", 1);
        params.put("sort", "asc");
        params.put("pagesize", 10);
        params.put("key", key);
        params.put("time", time);

        Call<JuHeResponse<Joke>> call;
        if (TextUtils.equals(type, "image")) {
            params.put("pagesize", 5);
            call = jokeApi.getJokeImageByTime(params);
        } else {
            call = jokeApi.getJokeByTime(params);
        }


        call.enqueue(new Callback<JuHeResponse<Joke>>() {
            @Override
            public void onResponse(Call<JuHeResponse<Joke>> call, Response<JuHeResponse<Joke>> response) {
                if (jokeListView == null) {
                    return;
                }

                JuHeResponse<Joke> body = response.body();
                if (body.getError_code() == 0) {
                    jokeListView.refreshData(true, body.getResult().getData());
                } else {
                    jokeListView.showError(body.getReason());
                }

            }

            @Override
            public void onFailure(Call<JuHeResponse<Joke>> call, Throwable t) {
                SMLog.e("JokePresenter", "onFailure--->", t);
                jokeListView.showError("请求异常");
            }
        });
    }
}
