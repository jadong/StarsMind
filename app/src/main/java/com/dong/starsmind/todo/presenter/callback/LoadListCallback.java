package com.dong.starsmind.todo.presenter.callback;

import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.handler.ResponseCallback;
import com.dong.starsmind.handler.response.ResponseDBLoadData;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.utils.AppUtils;

/**
 * Created by zengwendong on 16/11/1.
 */
public class LoadListCallback extends ResponseCallback<ResponseDBLoadData<TODO>> {

    private RefreshListView<DBPage<TODO>> view;

    public LoadListCallback(RefreshListView<DBPage<TODO>> view) {
        this.view = view;
    }

    @Override
    protected void onSuccess(ResponseDBLoadData<TODO> data) {
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
