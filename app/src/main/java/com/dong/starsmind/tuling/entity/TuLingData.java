package com.dong.starsmind.tuling.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 *
 *
 * 异常码(code) 说明
     40001 参数 key 错误
     40002 请求内容 info 为空
     40004 当天请求次数已使用完
     40007 数据格式异常
 *
 * Created by zengwendong on 16/11/24.
 */
public class TuLingData extends MessageEntity{

    /**
     * code: 100000 文本类
     200000 链接类
     302000 新闻类
     308000 菜谱类
     313000(儿童版) 儿歌类
     314000(儿童版) 诗词类
     */
    private int code;

    private String text;

    private String url;//链接地址

    private List<TLInfo> list;//信息列表

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<TLInfo> getList() {
        return list;
    }

    public void setList(List<TLInfo> list) {
        this.list = list;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
