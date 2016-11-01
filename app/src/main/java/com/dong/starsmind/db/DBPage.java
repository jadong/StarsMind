package com.dong.starsmind.db;

import java.util.List;

/**
 * Created by zengwendong on 16/10/30.
 */
public class DBPage<T> {

    private int pageNo;
    private int pageSize = 10;
    private int totalPage;//总页数
    private long rows;//总行数
    private String columnName;
    private boolean desc;//是否降序

    private List<T> dataList;//每页的数据集

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        if (pageSize > 0) {
            totalPage = (int) ((rows + pageSize - 1) / pageSize);
        }
        return totalPage;
    }

    public void orderBy(String columnName, boolean desc) {
        this.columnName = columnName;
        this.desc = desc;
    }

    public String getColumnName() {
        return columnName;
    }

    public boolean getDesc() {
        return desc;
    }

    public boolean hasNextPage(){
        if (pageNo >= totalPage) {
            return false;
        }
        return true;
    }
}
