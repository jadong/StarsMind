package com.dong.starsmind.movie.entity;

/**
 * Created by zengwendong on 16/12/12.
 */
public class StoryBean {

    /**
     * showname : 剧情
     * data : {"storyBrief":"影片改编自真实事件，2009年1月15日，前美国空军飞行员切斯利·萨伦伯格执飞全美航空1549号航..","storyMoreLink":"http://movie.mtime.com/225882/plots.html"}
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
         * storyBrief : 影片改编自真实事件，2009年1月15日，前美国空军飞行员切斯利·萨伦伯格执飞全美航空1549号航..
         * storyMoreLink : http://movie.mtime.com/225882/plots.html
         */

        private String storyBrief;
        private String storyMoreLink;

        public String getStoryBrief() {
            return storyBrief;
        }

        public void setStoryBrief(String storyBrief) {
            this.storyBrief = storyBrief;
        }

        public String getStoryMoreLink() {
            return storyMoreLink;
        }

        public void setStoryMoreLink(String storyMoreLink) {
            this.storyMoreLink = storyMoreLink;
        }
    }
}
