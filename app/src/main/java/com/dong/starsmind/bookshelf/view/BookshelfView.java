package com.dong.starsmind.bookshelf.view;

import com.dong.starsmind.bookshelf.entity.Book;
import com.dong.starsmind.common.view.LoadListView;

/**
 * Created by zengwendong on 16/11/3.
 */
public interface BookshelfView extends LoadListView<Book>{

    void addBookSuccess();

}
