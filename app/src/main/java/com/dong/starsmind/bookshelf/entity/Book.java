package com.dong.starsmind.bookshelf.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by zengwendong on 16/11/3.
 */
@Table(name = "book")
public class Book {

    @Column(name = "id",isId = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "read_progress")
    private int readProgress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getReadProgress() {
        return readProgress;
    }

    public void setReadProgress(int readProgress) {
        this.readProgress = readProgress;
    }
}
