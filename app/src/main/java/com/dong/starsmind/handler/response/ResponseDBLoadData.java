package com.dong.starsmind.handler.response;

import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.handler.ResponseData;
import com.dong.starsmind.todo.entity.TODO;

import java.util.List;

/**
 * Created by zengwendong on 16/10/30.
 */
public class ResponseDBLoadData<T> extends ResponseData {

    private DBPage<T> dbPage;

    public DBPage<T> getDbPage() {
        return dbPage;
    }

    public void setDbPage(DBPage<T> dbPage) {
        this.dbPage = dbPage;
    }
}
