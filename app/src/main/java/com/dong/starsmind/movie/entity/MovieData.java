package com.dong.starsmind.movie.entity;

import com.dong.starsmind.common.entity.JuHeResult;

/**
 * Created by zengwendong on 16/12/12.
 */
public class MovieData extends JuHeResult<MovieShowTime> {

    /**
     * title : 北京影讯_最近上映电影
     * url : http://theater.mtime.com/China_Beijing/
     * m_url : http://m.mtime.cn/?cityId=290
     */

    private String title;
    private String url;
    private String m_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getM_url() {
        return m_url;
    }

    public void setM_url(String m_url) {
        this.m_url = m_url;
    }

}
