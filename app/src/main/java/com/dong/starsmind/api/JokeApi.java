package com.dong.starsmind.api;

import com.dong.starsmind.common.entity.JuHeResponse;
import com.dong.starsmind.joke.entity.Joke;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 笑话大全
 * https://www.juhe.cn/docs/api/id/95/aid/281
 * Created by zengwendong on 16/12/5.
 */
public interface JokeApi {

    @GET("joke/content/text.from")
    Call<JuHeResponse<Joke>> getJokeList(@QueryMap HashMap<String,Object> params);

    @GET("joke/content/list.from")
    Call<JuHeResponse<Joke>> getJokeByTime(@QueryMap HashMap<String,Object> params);

    @GET("joke/img/text.from")
    Call<JuHeResponse<Joke>> getJokeImageList(@QueryMap HashMap<String,Object> params);

    @GET("joke/img/list.from")
    Call<JuHeResponse<Joke>> getJokeImageByTime(@QueryMap HashMap<String,Object> params);
}
