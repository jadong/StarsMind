package com.dong.starsmind.movie.presenter;

import com.dong.starsmind.api.GetMovieApi;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.constant.AppConstants;
import com.dong.starsmind.movie.entity.MovieData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zengwendong on 16/12/12.
 */
public class MovieDataPresenter {

    private final String key = "41d1fa3172ad6986d038c84b338f261e";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://japi.juhe.cn/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RefreshListView<MovieData> refreshListView;

    public MovieDataPresenter(RefreshListView<MovieData> refreshListView) {
        this.refreshListView = refreshListView;
    }

    public void getMovieData(){

        GetMovieApi getMovieApi = retrofit.create(GetMovieApi.class);

        HashMap<String,Object> params = new HashMap<>();
        params.put("key",key);
        params.put("city", AppConstants.city);

        Call<MovieData> movieData = getMovieApi.getMovieData(params);
        movieData.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                refreshListView.refreshData(response.body());
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {

            }
        });

    }
}
