package com.dong.starsmind.todo.presenter;

import android.text.TextUtils;

import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.handler.ResponseCallback;
import com.dong.starsmind.handler.ResponseData;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.view.TodoView;
import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.utils.ThreadPool;

/**
 * Created by zengwendong on 16/11/2.
 */
public class DeleteToDoPresenter {

    private TodoView todoView;

    public DeleteToDoPresenter(TodoView todoView) {
        this.todoView = todoView;
    }

    public void deleteToDoBy(final int id, final int deletePos) {
        final ResponseCallback<ResponseData> responseCallback = new ResponseCallback<ResponseData>() {
            @Override
            protected void onSuccess(ResponseData data) {
                todoView.onSuccess(data.getObject());
            }

            @Override
            protected void onFailed(ResponseData data) {
                if (TextUtils.isEmpty(data.getMessage())) {
                    AppUtils.toastShort("删除失败");
                } else {
                    AppUtils.toastShort(data.getMessage());
                }
            }
        };
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                ResponseData responseData = new ResponseData();
                boolean success = DBOperation.deleteById(TODO.class, id);
                if (success) {
                    responseData.setObject(deletePos);
                    responseData.setHandlerSuccess();
                } else {
                    responseData.setHandlerFailed();
                }
                responseCallback.processData(responseData);
            }
        });
    }

}
