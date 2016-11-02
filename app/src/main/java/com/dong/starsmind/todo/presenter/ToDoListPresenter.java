package com.dong.starsmind.todo.presenter;

import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.handler.response.ResponseTodoData;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.presenter.callback.ToDoListCallback;
import com.dong.starsmind.todo.view.ToDoListView;
import com.dong.starsmind.utils.ThreadPool;

import org.xutils.db.sqlite.WhereBuilder;

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
        dbPage.orderBy("reminder_time", true);//排序字段
    }

    public void loadToDoList(int pageNo, int status) {
        dbPage.setPageNo(pageNo);
        if (status > 0) {
            WhereBuilder whereBuilder = WhereBuilder.b().and("status", "=", status);
            dbPage.setWhereBuilder(whereBuilder);
        }
        final ToDoListCallback todoListCallback = new ToDoListCallback(view, dbPage);
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                List<TODO> todoList = DBOperation.findAll(TODO.class, dbPage);
                ResponseTodoData responseTodoData = new ResponseTodoData();
                responseTodoData.setDbPage(dbPage);
                if (todoList != null) {
                    responseTodoData.setHandlerSuccess();
                    responseTodoData.setDataList(todoList);
                } else {
                    responseTodoData.setHandlerFailed();
                }
                todoListCallback.processData(responseTodoData);
            }
        });

    }

}
