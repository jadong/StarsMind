package com.dong.starsmind.tuling.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by zengwendong on 16/11/26.
 */
@Table(name = "message_json")
public class MessageJson {

    public MessageJson() {
    }

    public MessageJson(int type, String content) {
        this.type = type;
        this.content = content;
    }

    @Column(name = "id",isId = true)
    private int id;

    @Column(name = "type")
    private int type;//=0我的消息,=1其它

    @Column(name = "content")
    private String content;//json内容

    @Column(name = "timestamp")
    private long timestamp = System.currentTimeMillis() / 1000;//当前时间,秒

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
