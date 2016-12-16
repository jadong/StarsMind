package com.dong.starsmind.historyevent.entity;

import java.util.List;

/**
 * Created by zengwendong on 16/12/15.
 */
public class HistoryEventDetail {

    /**
     * e_id : 13892
     * title : 罗马帝国皇帝尼禄出生
     * content :
     * picNo : 14
     * picUrl : [{"pic_title":"尼禄·克劳狄乌斯·德鲁苏斯·日耳曼尼库斯","id":1,"url":"http://images.juheapi.com/history/13892_1.jpg"},{"pic_title":"罗马帝国皇帝尼禄","id":2,"url":"http://images.juheapi.com/history/13892_2.jpg"},{"pic_title":"罗马帝国皇帝尼禄","id":3,"url":"http://images.juheapi.com/history/13892_3.jpg"},{"pic_title":"","id":4,"url":"http://images.juheapi.com/history/13892_4.jpg"},{"pic_title":"","id":5,"url":"http://images.juheapi.com/history/13892_5.jpg"},{"pic_title":"","id":6,"url":"http://images.juheapi.com/history/13892_6.jpg"},{"pic_title":"","id":7,"url":"http://images.juheapi.com/history/13892_7.jpg"},{"pic_title":"","id":8,"url":"http://images.juheapi.com/history/13892_8.jpg"},{"pic_title":"","id":9,"url":"http://images.juheapi.com/history/13892_9.jpg"},{"pic_title":"","id":10,"url":"http://images.juheapi.com/history/13892_10.jpg"},{"pic_title":"","id":11,"url":"http://images.juheapi.com/history/13892_11.jpg"},{"pic_title":"","id":12,"url":"http://images.juheapi.com/history/13892_12.jpg"},{"pic_title":"罗马帝国皇帝尼禄","id":13,"url":"http://images.juheapi.com/history/13892_13.jpg"},{"pic_title":"罗马帝国皇帝尼禄","id":14,"url":"http://images.juheapi.com/history/13892_14.jpg"}]
     */

    private String e_id;
    private String title;
    private String content;
    private String picNo;//图片数量
    private List<PicUrlBean> picUrl;

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicNo() {
        return picNo;
    }

    public void setPicNo(String picNo) {
        this.picNo = picNo;
    }

    public List<PicUrlBean> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List<PicUrlBean> picUrl) {
        this.picUrl = picUrl;
    }

    public static class PicUrlBean {
        /**
         * pic_title : 尼禄·克劳狄乌斯·德鲁苏斯·日耳曼尼库斯
         * id : 1
         * url : http://images.juheapi.com/history/13892_1.jpg
         */

        private String pic_title;//图片标题
        private int id;//图片顺序id
        private String url;//图片地址

        public String getPic_title() {
            return pic_title;
        }

        public void setPic_title(String pic_title) {
            this.pic_title = pic_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
