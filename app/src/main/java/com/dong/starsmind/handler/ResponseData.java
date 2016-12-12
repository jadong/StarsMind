package com.dong.starsmind.handler;

import com.dong.starsmind.constant.AppConstants;

/**
 * Created by zengwendong on 16/10/30.
 */
public class ResponseData {

    private int statusCode;//= 0 成功
    private String message;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setHandlerSuccess(){
        setStatusCode(AppConstants.STATUS_CODE_0);
    }

    public void setHandlerFailed(){
        setStatusCode(AppConstants.STATUS_CODE_1);
    }

    private void setStatusCode(@AppConstants.StatusCode int statusCode) {
        this.statusCode = statusCode;
    }
}
