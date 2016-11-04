package com.dong.starsmind.bookshelf.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.bookshelf.adapter.BookshelfAdapter;
import com.dong.starsmind.bookshelf.entity.Book;
import com.dong.starsmind.bookshelf.presenter.BookshelfPresenter;
import com.dong.starsmind.bookshelf.view.BookshelfView;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.home.MainActivity;
import com.dong.starsmind.todo.activity.AddToDoActivity;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

/**
 * Created by zengwendong on 16/11/3.
 */
public class BookshelfActivity extends BaseActivity implements BookshelfView {

    private FloatingActionButton fab_add;
    private LoadMoreRecyclerView rv_bookshelf;
    private BookshelfAdapter bookshelfAdapter;
    private BookshelfPresenter bookshelfPresenter;

    @Override
    protected String getToolBarTitle() {
        return getString(R.string.bookshelf_title);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_bookshelf;
    }

    @Override
    protected void initView() {
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        rv_bookshelf = (LoadMoreRecyclerView) findViewById(R.id.rv_bookshelf);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rv_bookshelf.setLayoutManager(gridLayoutManager);
        bookshelfAdapter = new BookshelfAdapter(this);
        rv_bookshelf.setAdapter(bookshelfAdapter);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bookshelfPresenter = new BookshelfPresenter(this);
        bookshelfPresenter.loadBookshelf();
    }

    @Override
    public void addBookSuccess() {
        bookshelfPresenter.loadBookshelf();
    }

    @Override
    public void refreshData(DBPage<Book> dbPage) {
        bookshelfAdapter.setData(dbPage.getDataList());
    }
}
