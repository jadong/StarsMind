package com.dong.starsmind.common.entity;

import java.util.List;

/**
 * Created by zengwendong on 16/12/5.
 */
public class JuHeResult<T> {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
