package com.dong.starsmind.tuling.presenter;

import com.dong.starsmind.api.TuLingApi;
import com.dong.starsmind.app.AppGlobal;
import com.dong.starsmind.app.SMLog;
import com.dong.starsmind.tuling.entity.MessageEntity;
import com.dong.starsmind.tuling.entity.MessageJson;
import com.dong.starsmind.tuling.entity.TuLingData;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.constant.AppConstants;
import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.utils.Aes;
import com.dong.starsmind.utils.Md5;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zengwendong on 16/11/24.
 */
public class TuLingPresenter {

    private RefreshListView<MessageEntity> refreshListView;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.tuling123.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public TuLingPresenter(RefreshListView<MessageEntity> refreshListView) {
        this.refreshListView = refreshListView;
    }

    public void sendMessage(final String info) {

        //待加密的json数据
        JSONObject dataObj = new JSONObject();
        try {
            dataObj.put("key", AppConstants.tuling_apiKey);
            dataObj.put("info", info);
            dataObj.put("loc", AppConstants.city);
            dataObj.put("userid", AppGlobal.uniqueId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data = dataObj.toString();

        //获取时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        //生成密钥secret + timestamp + baidu_apikey
        String keyParam = AppConstants.tuling_secret + timestamp + AppConstants.tuling_apiKey;
        String key = Md5.MD5(keyParam);

        //加密
        Aes mc = new Aes(key);
        data = mc.encrypt(data);

        //封装请求参数
        JSONObject json = new JSONObject();
        try {
            json.put("key", AppConstants.tuling_apiKey);
            json.put("timestamp", timestamp);
            json.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TuLingApi tuLingApi = retrofit.create(TuLingApi.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        Call<TuLingData> tuLingDataCall = tuLingApi.sendMessage(requestBody);
        tuLingDataCall.enqueue(new Callback<TuLingData>() {
            @Override
            public void onResponse(Call<TuLingData> call, Response<TuLingData> response) {
                TuLingData tuLingData = response.body();
                SMLog.i("TuLingPresenter", "response = [" + tuLingData.toJson() + "]");
                //保存数据库
                DBOperation.saveOrUpdate(new MessageJson(1, tuLingData.toJson()));
                refreshListView.refreshData(tuLingData);
            }

            @Override
            public void onFailure(Call<TuLingData> call, Throwable t) {
                SMLog.e("TuLingPresenter", "onFailure--->", t);
            }
        });
    }

    public DBPage<MessageEntity> getHistoryMessage(int pageNo) {
        DBPage<MessageJson> dbPage = new DBPage<>(10);
        dbPage.setPageNo(pageNo);
        dbPage.orderBy("id", true);
        dbPage = DBOperation.findAll(MessageJson.class, dbPage);

        List<MessageJson> dataList = dbPage.getDataList();
        List<MessageEntity> newDataList = new ArrayList<>();
        int size = dataList == null ? 0 : dataList.size();
        Gson gson = new Gson();
        for (int i = size - 1; i >= 0; i--) {
            MessageJson msgJson = dataList.get(i);
            if (msgJson.getType() == 0) {//我的消息
                MessageEntity entity = gson.fromJson(msgJson.getContent(), MessageEntity.class);
                newDataList.add(entity);
            } else {//=1其它
                TuLingData entity = gson.fromJson(msgJson.getContent(), TuLingData.class);
                newDataList.add(entity);
            }
        }

        DBPage<MessageEntity> dataPage = new DBPage<>(dbPage.getPageSize());
        dataPage.setRows(dbPage.getRows());
        dataPage.setPageNo(dbPage.getPageNo());
        dataPage.setDataList(newDataList);

        return dataPage;
    }

}
