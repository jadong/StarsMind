package com.dong.starsmind.common.entity;

/**
 * Created by zengwendong on 16/12/5.
 */
public class JuHeResponse<T> {

    private int error_code;
    private String reason;

    private JuHeResult<T> result;

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

    public JuHeResult<T> getResult() {
        return result;
    }

    public void setResult(JuHeResult<T> result) {
        this.result = result;
    }
}
