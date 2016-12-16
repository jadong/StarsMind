package com.dong.starsmind.historyevent.entity;

/**
 * Created by zengwendong on 16/12/16.
 */
public class NodeDetail {

    private String text;
    private String picUrl;
    private int type;//=1标题 =2详情内容 =3图片

    public NodeDetail(int type) {
        this.type = type;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
