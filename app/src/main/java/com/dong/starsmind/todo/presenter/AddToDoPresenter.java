package com.dong.starsmind.todo.presenter;

import android.text.TextUtils;

import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.handler.ResponseCallback;
import com.dong.starsmind.handler.ResponseData;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.view.AddTodoView;
import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.utils.ThreadPool;

/**
 * Created by zengwendong on 16/11/1.
 */
public class AddToDoPresenter {

    private AddTodoView addTodoView;

    public AddToDoPresenter(AddTodoView addTodoView) {
        this.addTodoView = addTodoView;
    }

    public TODO getToDo(int id) {
        return DBOperation.findById(TODO.class, id);
    }

    public void saveOrUpdate() {
        final ResponseCallback<ResponseData> responseCallback = new ResponseCallback<ResponseData>() {
            @Override
            protected void onSuccess(ResponseData data) {
                addTodoView.onSuccess(data.getObject());
            }

            @Override
            protected void onFailed(ResponseData data) {
                if (TextUtils.isEmpty(data.getMessage())) {
                    AppUtils.toastShort("保存失败");
                } else {
                    AppUtils.toastShort(data.getMessage());
                }
            }
        };
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                boolean success = false;
                ResponseData responseData = new ResponseData();
                if (TextUtils.isEmpty(addTodoView.getContent())) {
                    responseData.setMessage("待办事件不能为空");
                } else {
                    TODO todo = new TODO();
                    todo.setContent(addTodoView.getContent());
                    todo.setReminderTimeStr(addTodoView.getReminderTime());
                    success = DBOperation.saveOrUpdate(todo);
                }

                if (success) {
                    responseData.setHandlerSuccess();
                } else {
                    responseData.setHandlerFailed();
                }
                responseCallback.processData(responseData);
            }
        });

    }
}
