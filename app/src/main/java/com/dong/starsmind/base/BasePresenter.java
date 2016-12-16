package com.dong.starsmind.base;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zengwendong on 16/12/15.
 */
public abstract class BasePresenter {

    protected Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @NonNull
    protected abstract String getBaseUrl();

}
