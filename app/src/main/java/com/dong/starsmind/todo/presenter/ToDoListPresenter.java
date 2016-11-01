package com.dong.starsmind.todo.presenter;

import com.dong.starsmind.constant.AppConstant;
import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.handler.ResponseCallback;
import com.dong.starsmind.handler.response.ResponseTodoData;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.view.ToDoListView;
import com.dong.starsmind.utils.ThreadPool;

import java.util.List;

/**
 * Created by zengwendong on 16/10/30.
 */
public class ToDoListPresenter {

    private ToDoListView view;
    private DBPage<TODO> dbPage;

    public ToDoListPresenter(ToDoListView view) {
        this.view = view;
        dbPage = new DBPage<>();
        dbPage.orderBy("timestamp",true);
    }

    private ResponseCallback<ResponseTodoData> responseCallback = new ResponseCallback<ResponseTodoData>() {

        @Override
        protected void onSuccess(ResponseTodoData data) {
            dbPage.setDataList(data.getDataList());
            view.refreshData(dbPage);
        }

        @Override
        protected void onFailed(ResponseTodoData data) {

        }

    };

    public void loadToDoList(int pageNo) {
        dbPage.setPageNo(pageNo);
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                List<TODO> todoList = DBOperation.findAll(TODO.class, dbPage);
                ResponseTodoData responseTodoData = new ResponseTodoData();
                responseTodoData.setDbPage(dbPage);
                if (todoList != null) {
                    responseTodoData.setStatusCode(AppConstant.STATUSCODE_0);
                    responseTodoData.setDataList(todoList);
                } else {
                    responseTodoData.setStatusCode(AppConstant.STATUSCODE_1);
                }

                responseCallback.processData(responseTodoData);
            }
        });

    }

}
