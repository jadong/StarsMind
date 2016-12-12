package com.dong.starsmind.read.entity;

import java.util.List;

/**
 * Created by zengwendong on 16/11/8.
 */
public class RankData {

    private int hasMore;

    private List<Book> novelList;

    private int total;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<Book> getNovelList() {
        return novelList;
    }

    public void setNovelList(List<Book> novelList) {
        this.novelList = novelList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
