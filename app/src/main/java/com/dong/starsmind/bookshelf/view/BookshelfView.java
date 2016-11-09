package com.dong.starsmind.bookshelf.view;

import com.dong.starsmind.bookshelf.entity.Book;

import java.util.List;

/**
 * Created by zengwendong on 16/11/3.
 */
public interface BookshelfView {

    void addBookSuccess();

    void refreshData(List<Book> bookList);

}
