package com.dong.starsmind.historyevent.presenter;

import com.dong.starsmind.R;
import com.dong.starsmind.api.HistoryEventApi;
import com.dong.starsmind.base.BasePresenter;
import com.dong.starsmind.common.entity.JuHeDataResponse;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.historyevent.entity.HistoryEvent;
import com.dong.starsmind.historyevent.entity.HistoryEventDetail;
import com.dong.starsmind.historyevent.entity.NodeDetail;
import com.dong.starsmind.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zengwendong on 16/12/15.
 */
public class HistoryDetailPresenter extends BasePresenter {

    private final String key = "0cfaccad48bb8af3f281f1403c130012";
    private RefreshListView<List<NodeDetail>> refreshListView;

    public HistoryDetailPresenter(RefreshListView<List<NodeDetail>> refreshListView) {
        this.refreshListView = refreshListView;
    }

    @Override
    protected String getBaseUrl() {
        return "http://v.juhe.cn/";
    }

    public void getHistoryEventDetail(String eventId) {
        HistoryEventApi historyEventApi = retrofit.create(HistoryEventApi.class);
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("e_id", eventId);
        Call<JuHeDataResponse<HistoryEventDetail>> call = historyEventApi.getHistoryEventDetail(params);
        call.enqueue(new Callback<JuHeDataResponse<HistoryEventDetail>>() {
            @Override
            public void onResponse(Call<JuHeDataResponse<HistoryEventDetail>> call, Response<JuHeDataResponse<HistoryEventDetail>> response) {
                if (response.body() == null) {
                    AppUtils.toastShort(AppUtils.getString(R.string.txt_data_exception));
                    return;
                }
                if (refreshListView != null) {
                    List<HistoryEventDetail> result = response.body().getResult();
                    if (result != null) {
                        List<NodeDetail> nodeDetails = new ArrayList<>();
                        for (int i = 0; i < result.size(); i++) {
                            HistoryEventDetail detail = result.get(i);

                            NodeDetail nodeDetail1 = new NodeDetail(1);//标题
                            nodeDetail1.setText(detail.getTitle());
                            nodeDetails.add(nodeDetail1);

                            List<HistoryEventDetail.PicUrlBean> picUrlList = detail.getPicUrl();
                            int picNum = picUrlList == null ? 0 : picUrlList.size();
                            for (int j = 0; j < picNum; j++) {
                                HistoryEventDetail.PicUrlBean picUrlBean = picUrlList.get(j);
                                NodeDetail nodeDetail = new NodeDetail(3);//图片
                                nodeDetail.setText(picUrlBean.getPic_title());
                                nodeDetail.setPicUrl(picUrlBean.getUrl());
                                nodeDetails.add(nodeDetail);
                            }

                            NodeDetail nodeDetail2 = new NodeDetail(2);//内容
                            nodeDetail2.setText(detail.getContent());
                            nodeDetails.add(nodeDetail2);

                        }
                        refreshListView.refreshData(nodeDetails);
                    } else {
                        AppUtils.toastShort(AppUtils.getString(R.string.txt_data_exception));
                    }
                }
            }

            @Override
            public void onFailure(Call<JuHeDataResponse<HistoryEventDetail>> call, Throwable t) {
                AppUtils.toastShort(AppUtils.getString(R.string.txt_data_exception));
            }
        });
    }


}
