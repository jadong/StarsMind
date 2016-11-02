package com.dong.starsmind.handler;

import android.os.Handler;
import android.os.Message;

/**
 * 回调处理,需在主线程创建
 * Created by zengwendong on 16/10/30.
 */
public abstract class ResponseCallback<T extends ResponseData> extends Handler {

    protected abstract void onSuccess(T data);

    protected abstract void onFailed(T data);

    public void processData(ResponseData data) {
        obtainMessage(data.getStatusCode(), data).sendToTarget();
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg != null) {
            T data = (T) msg.obj;
            if (msg.what == 0) {
                onSuccess(data);
            } else {
                onFailed(data);
            }
        }
    }
}
