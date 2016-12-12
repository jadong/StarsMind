package com.dong.starsmind.news.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zengwendong on 16/11/21.
 */
public class News {

    @SerializedName("title")
    private String title;//文字标题

    @SerializedName("description")
    private String description; //来源及描述

    @SerializedName("picUrl")
    private String picUrl;//图片，若空为默认

    @SerializedName("url")
    private String url;//文章URL

    @SerializedName("ctime")
    private String ctime;//文章发布时间

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

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
}
