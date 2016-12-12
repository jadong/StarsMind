package com.dong.starsmind.movie.entity;

import java.util.List;

/**
 * Created by zengwendong on 16/12/12.
 */
public class MovieShowTime {
    private String name;//即将上映 或 正在上映
    private String link;
    private List<Movie> data;

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
