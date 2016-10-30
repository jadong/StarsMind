package com.dong.starsmind.handler;

import com.dong.starsmind.constant.AppConstant;

/**
 * Created by zengwendong on 16/10/30.
 */
public class ResponseData {

    private int statusCode;//= 0 成功

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(@AppConstant.StatusCode int statusCode) {
        this.statusCode = statusCode;
    }
}
