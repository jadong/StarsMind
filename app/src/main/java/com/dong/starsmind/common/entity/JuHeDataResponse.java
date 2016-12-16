package com.dong.starsmind.common.entity;

import java.util.List;

/**
 * Created by zengwendong on 16/12/5.
 */
public class JuHeDataResponse<T> {

    private int error_code;
    private String reason;
    private List<T> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
