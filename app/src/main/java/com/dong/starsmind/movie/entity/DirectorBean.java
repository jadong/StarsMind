package com.dong.starsmind.movie.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zengwendong on 16/12/12.
 */
public class DirectorBean {


    /**
     * showname : 导演
     * data : {"1":{"name":"克林特·伊斯特伍德","link":"http://people.mtime.com/892756/"},"m_1":{"link":"http://m.mtime.cn/#!/person/892756/"}}
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
         * 1 : {"name":"克林特·伊斯特伍德","link":"http://people.mtime.com/892756/"}
         * m_1 : {"link":"http://m.mtime.cn/#!/person/892756/"}
         */

        @SerializedName("1")
        private NameLink value1;

        private NameLink m_1;

        public NameLink getValue1() {
            return value1;
        }

        public void setValue1(NameLink value1) {
            this.value1 = value1;
        }

        public NameLink getM_1() {
            return m_1;
        }

        public void setM_1(NameLink m_1) {
            this.m_1 = m_1;
        }

    }
}
