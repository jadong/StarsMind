package com.dong.starsmind.todo.presenter.callback;

import com.dong.starsmind.handler.ResponseCallback;
import com.dong.starsmind.handler.response.ResponseDBLoadData;
import com.dong.starsmind.common.view.LoadListView;
import com.dong.starsmind.utils.AppUtils;

/**
 * Created by zengwendong on 16/11/1.
 */
public class LoadListCallback<T> extends ResponseCallback<ResponseDBLoadData<T>> {

    private LoadListView<T> view;

    public LoadListCallback(LoadListView<T> view) {
        this.view = view;
    }

    @Override
    protected void onSuccess(ResponseDBLoadData<T> data) {
        if (data.getDbPage() != null) {
            view.refreshData(data.getDbPage());
        } else {
            onFailed(data);
        }
    }

    @Override
    protected void onFailed(ResponseDBLoadData data) {
        AppUtils.toastShort("获取数据失败");
    }

}
