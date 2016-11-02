package com.dong.starsmind.todo.presenter;

import android.text.TextUtils;

import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.handler.ResponseCallback;
import com.dong.starsmind.handler.ResponseData;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.view.UpdateTodoView;
import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.utils.ThreadPool;

/**
 * Created by zengwendong on 16/11/2.
 */
public class UpdateToDoPresenter {

    private UpdateTodoView updateTodoView;

    public UpdateToDoPresenter(UpdateTodoView updateTodoView) {
        this.updateTodoView = updateTodoView;
    }

    public TODO getToDo(int id) {
        return DBOperation.findById(TODO.class, id);
    }

    public void saveOrUpdate() {
        final ResponseCallback<ResponseData> responseCallback = new ResponseCallback<ResponseData>() {
            @Override
            protected void onSuccess(ResponseData data) {
                updateTodoView.onSuccess(data.getObject());
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
                TODO todo = updateTodoView.getTodo();
                ResponseData responseData = new ResponseData();
                boolean success = false;
                if (todo != null) {
                    if (!TextUtils.isEmpty(todo.getContent())) {
                        success = DBOperation.saveOrUpdate(todo);
                    } else {
                        responseData.setMessage("待办事件不能为空");
                    }
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
