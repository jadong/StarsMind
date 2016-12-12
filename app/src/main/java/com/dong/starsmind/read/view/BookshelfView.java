package com.dong.starsmind.read.view;

import com.dong.starsmind.read.entity.Book;

import java.util.List;

/**
 * Created by zengwendong on 16/11/3.
 */
public interface BookshelfView {

    void addBookSuccess();

    void refreshData(List<Book> bookList);

}
