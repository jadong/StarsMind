package com.dong.starsmind.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zengwendong on 16/11/8.
 */
public interface GetDataApi {

    /**
     *
     */
    @GET("api/getRankDetailData?page=1&count=20&cate=玄幻&query=玄幻热门榜")
    Call<ResponseBody> getBookList();

}
