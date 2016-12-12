package com.dong.starsmind.news.presenter;

import android.text.TextUtils;

import com.dong.starsmind.api.WXInformationApi;
import com.dong.starsmind.app.SMLog;
import com.dong.starsmind.news.view.RefreshNewsListView;
import com.dong.starsmind.news.entity.ResponseEntity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zengwendong on 16/11/21.
 */
public class WXHotPresenter {

    private RefreshNewsListView refreshListView;

    public WXHotPresenter(RefreshNewsListView refreshListView) {
        this.refreshListView = refreshListView;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://apis.baidu.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getWXInformation(String keyword, int page) {

        //API文档 http://apistore.baidu.com/apiworks/servicedetail/632.html
        WXInformationApi wxInformationApi = retrofit.create(WXInformationApi.class);
        HashMap<String, String> map = new HashMap<>(10);
        map.put("page", page + "");
        map.put("num", "10");
        map.put("rand", "2");
        if (!TextUtils.isEmpty(keyword)) {
            map.put("word", keyword);
        }
        //map.put("src","");

        Call<ResponseEntity> wxInformation = wxInformationApi.getWXInformation(map);
        wxInformation.enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                if (refreshListView == null) {
                    return;
                }
                try {
                    ResponseEntity responseEntity = response.body();
                    if (responseEntity != null) {
                        if (responseEntity.getCode() == 200) {
                            refreshListView.refreshData(responseEntity.getNewslist());
                        } else {
                            refreshListView.noContentFound(responseEntity.getMsg());
                        }
                    } else {
                        SMLog.e("WXHotPresenter", "Exception--->response.body() is null");
                        refreshListView.noContentFound("暂无数据");
                    }


                } catch (Exception e) {
                    SMLog.e("WXHotPresenter", "Exception--->", e);
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                SMLog.e("WXHotPresenter", "onFailure--->", t);
            }
        });
    }

}
