package com.dong.starsmind.todo.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by zengwendong on 16/10/27.
 */
@Table(name = "todo")
public class TODO {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private long timestamp = System.currentTimeMillis() / 1000;//当前时间,秒

    @Column(name = "reminder_time")
    private long reminderTime;//提醒时间

    @Column(name = "status")
    private int status;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(long reminderTime) {
        this.reminderTime = reminderTime;
    }
}
