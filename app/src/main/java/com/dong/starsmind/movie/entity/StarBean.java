package com.dong.starsmind.movie.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zengwendong on 16/12/12.
 */
public class StarBean {


    /**
     * showname : 主演
     * data : {"1":{"name":"汤姆·汉克斯","link":"http://people.mtime.com/901704/"},"m_1":{"link":"http://m.mtime.cn/#!/person/901704/"},"2":{"name":"艾伦·艾克哈特","link":"http://people.mtime.com/937730/"},"m_2":{"link":"http://m.mtime.cn/#!/person/937730/"},"3":{"name":"劳拉·琳妮","link":"http://people.mtime.com/921395/"},"m_3":{"link":"http://m.mtime.cn/#!/person/921395/"},"4":{"name":"安娜·古恩","link":"http://people.mtime.com/985329/"},"m_4":{"link":"http://m.mtime.cn/#!/person/985329/"}}
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
         * 1 : {"name":"汤姆·汉克斯","link":"http://people.mtime.com/901704/"}
         * m_1 : {"link":"http://m.mtime.cn/#!/person/901704/"}
         * 2 : {"name":"艾伦·艾克哈特","link":"http://people.mtime.com/937730/"}
         * m_2 : {"link":"http://m.mtime.cn/#!/person/937730/"}
         * 3 : {"name":"劳拉·琳妮","link":"http://people.mtime.com/921395/"}
         * m_3 : {"link":"http://m.mtime.cn/#!/person/921395/"}
         * 4 : {"name":"安娜·古恩","link":"http://people.mtime.com/985329/"}
         * m_4 : {"link":"http://m.mtime.cn/#!/person/985329/"}
         */

        @SerializedName("1")
        private NameLink value1;

        private NameLink m_1;

        @SerializedName("2")
        private NameLink value2;

        private NameLink m_2;

        @SerializedName("3")
        private NameLink value3;

        private NameLink m_3;

        @SerializedName("4")
        private NameLink value4;

        private NameLink m_4;

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

        public NameLink getValue2() {
            return value2;
        }

        public void setValue2(NameLink value2) {
            this.value2 = value2;
        }

        public NameLink getM_2() {
            return m_2;
        }

        public void setM_2(NameLink m_2) {
            this.m_2 = m_2;
        }

        public NameLink getValue3() {
            return value3;
        }

        public void setValue3(NameLink value3) {
            this.value3 = value3;
        }

        public NameLink getM_3() {
            return m_3;
        }

        public void setM_3(NameLink m_3) {
            this.m_3 = m_3;
        }

        public NameLink getValue4() {
            return value4;
        }

        public void setValue4(NameLink value4) {
            this.value4 = value4;
        }

        public NameLink getM_4() {
            return m_4;
        }

        public void setM_4(NameLink m_4) {
            this.m_4 = m_4;
        }


    }
}
