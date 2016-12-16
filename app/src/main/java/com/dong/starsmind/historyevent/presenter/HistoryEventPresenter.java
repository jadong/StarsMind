package com.dong.starsmind.historyevent.presenter;

import com.dong.starsmind.R;
import com.dong.starsmind.api.HistoryEventApi;
import com.dong.starsmind.base.BasePresenter;
import com.dong.starsmind.historyevent.entity.HistoryEvent;
import com.dong.starsmind.common.entity.JuHeDataResponse;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.utils.AppUtils;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zengwendong on 16/12/15.
 */
public class HistoryEventPresenter extends BasePresenter {

    private final String key = "0cfaccad48bb8af3f281f1403c130012";
    private RefreshListView<List<HistoryEvent>> refreshListView;

    public HistoryEventPresenter(RefreshListView<List<HistoryEvent>> refreshListView) {
        this.refreshListView = refreshListView;
    }

    @Override
    protected String getBaseUrl() {
        return "http://v.juhe.cn/";
    }

    public void getHistoryEvent(String date) {
        HistoryEventApi historyEventApi = retrofit.create(HistoryEventApi.class);
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("date", date);
        Call<JuHeDataResponse<HistoryEvent>> call = historyEventApi.getHistoryEvent(params);
        call.enqueue(new Callback<JuHeDataResponse<HistoryEvent>>() {
            @Override
            public void onResponse(Call<JuHeDataResponse<HistoryEvent>> call, Response<JuHeDataResponse<HistoryEvent>> response) {
                if (response.body() == null) {
                    AppUtils.toastShort(AppUtils.getString(R.string.txt_data_exception));
                    return;
                }
                if (refreshListView != null) {
                    refreshListView.refreshData(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<JuHeDataResponse<HistoryEvent>> call, Throwable t) {
                AppUtils.toastShort(AppUtils.getString(R.string.txt_data_exception));
            }
        });
    }


}
