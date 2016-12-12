package com.dong.starsmind.movie.entity;

import java.util.List;

/**
 * Created by zengwendong on 16/12/12.
 */
public class MoreBean {

    /**
     * data : [{"name":"选座购票","link":"http://theater.mtime.com/China_Beijing/movie/225882/"},{"name":"剧照/海报","link":"http://movie.mtime.com/225882/posters_and_images/"},{"name":"热门影评","link":"http://movie.mtime.com/225882/comment.html"},{"link":"http://m.mtime.cn/#!/movie/225882/posters_and_images/"},{"link":"http://m.mtime.cn/#!/movie/225882/comment/"},{"link":"http://m.mtime.cn/#!/theater/290/movie/225882/"}]
     * showname : 更多
     */

    private String showname;
    private List<NameLink> data;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public List<NameLink> getData() {
        return data;
    }

    public void setData(List<NameLink> data) {
        this.data = data;
    }


}
