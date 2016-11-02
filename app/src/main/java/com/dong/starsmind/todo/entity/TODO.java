package com.dong.starsmind.todo.entity;

import android.text.TextUtils;

import com.dong.starsmind.utils.TimeUtils;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by zengwendong on 16/10/27.
 */
@Table(name = "todo")
public class TODO {

    //---------任务状态-------
    public static final int STATUS_DONE = 0;//已完成
    public static final int STATUS_UNDERWAY = 1;//正在进行
    public static final int STATUS_UNFINISHED = 2;//待完成

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "content_bytes")
    private byte[] bytes;//内容字节数组

    @Column(name = "timestamp")
    private long timestamp = System.currentTimeMillis() / 1000;//当前时间,秒

    @Column(name = "reminder_time")
    private long reminderTime;//提醒时间

    @Column(name = "status")
    private int status = 2;//任务状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
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

    public String getReminderTimeStr() {
        return TimeUtils.formatTimeToString(reminderTime);
    }

    public void setReminderTimeStr(String reminderTime) {
        this.reminderTime = TimeUtils.parseToTimestamp(reminderTime);
    }


    public String getContent() {
        String text = "";
        if (bytes != null) {
            try {
                text = new String(bytes,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return text;
    }

    public void setContent(String content) {

        try {
            if (!TextUtils.isEmpty(content)) {
                this.bytes = content.getBytes("UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
