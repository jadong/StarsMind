package com.dong.starsmind.todo.presenter;

import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.handler.response.ResponseDBLoadData;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.presenter.callback.LoadListCallback;
import com.dong.starsmind.utils.ThreadPool;

import org.xutils.db.sqlite.WhereBuilder;

/**
 * Created by zengwendong on 16/10/30.
 */
public class ToDoListPresenter {

    private RefreshListView<DBPage<TODO>> view;
    private DBPage<TODO> dbPage;

    public ToDoListPresenter(RefreshListView<DBPage<TODO>> view) {
        this.view = view;
        dbPage = new DBPage<>();
        dbPage.orderBy("reminder_time", true);//排序字段
    }

    public void loadToDoList(int pageNo, int status) {
        dbPage.setPageNo(pageNo);
        if (status > 0) {
            WhereBuilder whereBuilder = WhereBuilder.b().and("status", "=", status);
            dbPage.setWhereBuilder(whereBuilder);
        }
        final LoadListCallback todoListCallback = new LoadListCallback(view);
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                DBOperation.findAll(TODO.class, dbPage);
                ResponseDBLoadData<TODO> responseTodoData = new ResponseDBLoadData<>();
                responseTodoData.setHandlerSuccess();
                responseTodoData.setDbPage(dbPage);
                todoListCallback.processData(responseTodoData);
            }
        });

    }

}
