package com.dong.starsmind.bookshelf.presenter;

import com.dong.starsmind.bookshelf.entity.Book;
import com.dong.starsmind.bookshelf.view.BookshelfView;
import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.handler.response.ResponseDBLoadData;
import com.dong.starsmind.todo.presenter.callback.LoadListCallback;
import com.dong.starsmind.utils.ThreadPool;

/**
 * Created by zengwendong on 16/11/3.
 */
public class BookshelfPresenter {

    private BookshelfView bookshelfView;
    private DBPage<Book> dbPage = new DBPage<>();

    public BookshelfPresenter(BookshelfView bookshelfView) {
        this.bookshelfView = bookshelfView;
    }

    public void loadBookshelf() {

        final LoadListCallback<Book> todoListCallback = new LoadListCallback<>(bookshelfView);
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                DBOperation.findAll(Book.class, dbPage);
                ResponseDBLoadData<Book> responseTodoData = new ResponseDBLoadData<>();
                if (dbPage.getDataList() != null) {
                    responseTodoData.setHandlerSuccess();
                    responseTodoData.setDbPage(dbPage);
                } else {
                    responseTodoData.setHandlerFailed();
                }
                todoListCallback.processData(responseTodoData);
            }
        });
    }

}
