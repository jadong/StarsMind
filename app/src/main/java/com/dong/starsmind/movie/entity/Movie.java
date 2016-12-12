package com.dong.starsmind.movie.entity;

/**
 * Created by zengwendong on 16/12/12.
 */
public class Movie {


    /**
     * tvTitle : 萨利机长
     * iconaddress : http://p5.qhmsg.com/t017e421dc61f295fc1.jpg?size=300x400
     * iconlinkUrl : http://movie.mtime.com/225882/
     * m_iconlinkUrl : http://m.mtime.cn/#!/movie/225882/
     * subHead : 今日141家影院上映
     * grade : 8.1
     * gradeNum : /10.0(时光网)
     * playDate : {"showname":"上映日期","data":"2016年12月9日","data2":"2016-12-9"}
     * director : {"showname":"导演","data":{"1":{"name":"克林特·伊斯特伍德","link":"http://people.mtime.com/892756/"},"m_1":{"link":"http://m.mtime.cn/#!/person/892756/"}}}
     * star : {"showname":"主演","data":{"1":{"name":"汤姆·汉克斯","link":"http://people.mtime.com/901704/"},"m_1":{"link":"http://m.mtime.cn/#!/person/901704/"},"2":{"name":"艾伦·艾克哈特","link":"http://people.mtime.com/937730/"},"m_2":{"link":"http://m.mtime.cn/#!/person/937730/"},"3":{"name":"劳拉·琳妮","link":"http://people.mtime.com/921395/"},"m_3":{"link":"http://m.mtime.cn/#!/person/921395/"},"4":{"name":"安娜·古恩","link":"http://people.mtime.com/985329/"},"m_4":{"link":"http://m.mtime.cn/#!/person/985329/"}}}
     * type : {"showname":"类型","data":{"1":{"name":"传记","link":"http://movie.mtime.com/movie/search/section/?type=Biography"},"2":{"name":"剧情","link":"http://movie.mtime.com/movie/search/section/?type=Drama"}}}
     * story : {"showname":"剧情","data":{"storyBrief":"影片改编自真实事件，2009年1月15日，前美国空军飞行员切斯利·萨伦伯格执飞全美航空1549号航..","storyMoreLink":"http://movie.mtime.com/225882/plots.html"}}
     * more : {"data":[{"name":"选座购票","link":"http://theater.mtime.com/China_Beijing/movie/225882/"},{"name":"剧照/海报","link":"http://movie.mtime.com/225882/posters_and_images/"},{"name":"热门影评","link":"http://movie.mtime.com/225882/comment.html"},{"link":"http://m.mtime.cn/#!/movie/225882/posters_and_images/"},{"link":"http://m.mtime.cn/#!/movie/225882/comment/"},{"link":"http://m.mtime.cn/#!/theater/290/movie/225882/"}],"showname":"更多"}
     */

    private String tvTitle;
    private String iconaddress;
    private String iconlinkUrl;
    private String m_iconlinkUrl;
    private String subHead;
    private String grade;
    private String gradeNum;
    private PlayDateBean playDate;
    private DirectorBean director;
    private StarBean star;
    private TypeBean type;
    private StoryBean story;
    private MoreBean more;

    public DirectorBean getDirector() {
        return director;
    }

    public void setDirector(DirectorBean director) {
        this.director = director;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getIconaddress() {
        return iconaddress;
    }

    public void setIconaddress(String iconaddress) {
        this.iconaddress = iconaddress;
    }

    public String getIconlinkUrl() {
        return iconlinkUrl;
    }

    public void setIconlinkUrl(String iconlinkUrl) {
        this.iconlinkUrl = iconlinkUrl;
    }

    public String getM_iconlinkUrl() {
        return m_iconlinkUrl;
    }

    public void setM_iconlinkUrl(String m_iconlinkUrl) {
        this.m_iconlinkUrl = m_iconlinkUrl;
    }

    public MoreBean getMore() {
        return more;
    }

    public void setMore(MoreBean more) {
        this.more = more;
    }

    public PlayDateBean getPlayDate() {
        return playDate;
    }

    public void setPlayDate(PlayDateBean playDate) {
        this.playDate = playDate;
    }

    public StarBean getStar() {
        return star;
    }

    public void setStar(StarBean star) {
        this.star = star;
    }

    public StoryBean getStory() {
        return story;
    }

    public void setStory(StoryBean story) {
        this.story = story;
    }

    public String getSubHead() {
        return subHead;
    }

    public void setSubHead(String subHead) {
        this.subHead = subHead;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TypeBean getType() {
        return type;
    }

    public void setType(TypeBean type) {
        this.type = type;
    }
}
