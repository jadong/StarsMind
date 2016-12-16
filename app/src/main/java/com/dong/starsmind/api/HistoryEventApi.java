package com.dong.starsmind.api;

import com.dong.starsmind.historyevent.entity.HistoryEvent;
import com.dong.starsmind.historyevent.entity.HistoryEventDetail;
import com.dong.starsmind.common.entity.JuHeDataResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * https://www.juhe.cn/docs/api/id/63/aid/849
 * Created by zengwendong on 16/12/15.
 */
public interface HistoryEventApi {

    @GET("todayOnhistory/queryEvent.php")
    Call<JuHeDataResponse<HistoryEvent>> getHistoryEvent(@QueryMap HashMap<String,Object> params);

    @GET("todayOnhistory/queryDetail.php")
    Call<JuHeDataResponse<HistoryEventDetail>> getHistoryEventDetail(@QueryMap HashMap<String,Object> params);
}
