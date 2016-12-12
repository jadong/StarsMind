package com.dong.starsmind.movie.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zengwendong on 16/12/12.
 */
public class TypeBean {

    /**
     * showname : 类型
     * data : {"1":{"name":"传记","link":"http://movie.mtime.com/movie/search/section/?type=Biography"},"2":{"name":"剧情","link":"http://movie.mtime.com/movie/search/section/?type=Drama"}}
     */

    private String showname;
    private DataBean data;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * 1 : {"name":"传记","link":"http://movie.mtime.com/movie/search/section/?type=Biography"}
         * 2 : {"name":"剧情","link":"http://movie.mtime.com/movie/search/section/?type=Drama"}
         */

        @SerializedName("1")
        private NameLink value1;
        @SerializedName("2")
        private NameLink value2;

        public NameLink getValue1() {
            return value1;
        }

        public void setValue1(NameLink value1) {
            this.value1 = value1;
        }

        public NameLink getValue2() {
            return value2;
        }

        public void setValue2(NameLink value2) {
            this.value2 = value2;
        }

    }
}
