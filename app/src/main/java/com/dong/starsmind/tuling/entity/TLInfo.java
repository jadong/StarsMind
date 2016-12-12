package com.dong.starsmind.tuling.entity;

/**
 * Created by zengwendong on 16/11/24.
 */
public class TLInfo {

    private String article;//新闻标题

    private String source;//新闻来源

    private String icon;//信息图片

    private String detailurl;//新闻详情链接

    private String name;//菜名

    private String info;//菜谱信息

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDetailurl() {
        return detailurl;
    }

    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
