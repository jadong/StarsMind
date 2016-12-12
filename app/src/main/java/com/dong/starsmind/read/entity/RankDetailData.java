package com.dong.starsmind.read.entity;

/**
 * Created by zengwendong on 16/11/8.
 */
public class RankDetailData {

    private int errno;
    private String msg;

    private RankData data;

    public RankData getData() {
        return data;
    }

    public void setData(RankData data) {
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
