package com.dong.starsmind.news.presenter;

import com.dong.starsmind.api.HealthApi;
import com.dong.starsmind.app.SMLog;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.news.entity.News;
import com.dong.starsmind.news.entity.ResponseEntity;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zengwendong on 16/11/21.
 */
public class HealthNewsPresenter {

    private RefreshListView<List<News>> refreshListView;

    public HealthNewsPresenter(RefreshListView<List<News>> refreshListView) {
        this.refreshListView = refreshListView;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://apis.baidu.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getMeiNvImages(int page) {

        //API文档 http://apistore.baidu.com/apiworks/servicedetail/989.html
        HealthApi healthApi = retrofit.create(HealthApi.class);
        HashMap<String, String> map = new HashMap<>(10);
        map.put("", page + "");
        map.put("num", "10");
        map.put("rand", "2");

        Call<ResponseEntity> wxInformation = healthApi.getMeiNvImages(map);
        wxInformation.enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                try {
                    refreshListView.refreshData(response.body().getNewslist());

                } catch (Exception e) {
                    SMLog.e("HealthNewsPresenter", "Exception--->", e);
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                SMLog.e("HealthNewsPresenter", "onFailure--->", t);
            }
        });
    }

}
