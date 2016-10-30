package com.dong.starsmind.handler.response;

import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.handler.ResponseData;
import com.dong.starsmind.todo.entity.TODO;

import java.util.List;

/**
 * Created by zengwendong on 16/10/30.
 */
public class ResponseTodoData extends ResponseData {

    private DBPage dbPage = new DBPage();

    private List<TODO> dataList;

    public List<TODO> getDataList() {
        return dataList;
    }

    public void setDataList(List<TODO> dataList) {
        this.dataList = dataList;
    }

    public DBPage getDbPage() {
        return dbPage;
    }

    public void setDbPage(DBPage dbPage) {
        this.dbPage = dbPage;
    }
}
