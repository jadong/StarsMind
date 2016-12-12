package com.dong.starsmind.api;

import com.dong.starsmind.movie.entity.MovieData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 *
 * Created by zengwendong on 16/12/12.
 */
public interface GetMovieApi {

    /**
     * https://www.juhe.cn/docs/api/id/94/aid/250
     *
     */
    @GET("onebox/movie/pmovie")
    Call<MovieData> getMovieData(@QueryMap HashMap<String,Object> params);

}
