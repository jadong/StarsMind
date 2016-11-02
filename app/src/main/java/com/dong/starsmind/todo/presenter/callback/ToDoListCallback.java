package com.dong.starsmind.todo.presenter.callback;

import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.handler.ResponseCallback;
import com.dong.starsmind.handler.response.ResponseTodoData;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.view.ToDoListView;

/**
 * Created by zengwendong on 16/11/1.
 */
public class ToDoListCallback extends ResponseCallback<ResponseTodoData> {

    private DBPage<TODO> dbPage;
    private ToDoListView view;

    public ToDoListCallback(ToDoListView view, DBPage<TODO> dbPage) {
        this.dbPage = dbPage;
        this.view = view;
    }

    @Override
    protected void onSuccess(ResponseTodoData data) {
        dbPage.setDataList(data.getDataList());
        view.refreshData(dbPage);
    }

    @Override
    protected void onFailed(ResponseTodoData data) {

    }

}
