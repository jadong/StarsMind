package com.dong.starsmind.bookshelf.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.bookshelf.adapter.BookshelfAdapter;
import com.dong.starsmind.bookshelf.entity.Book;
import com.dong.starsmind.bookshelf.entity.RankDetailData;
import com.dong.starsmind.bookshelf.presenter.BookshelfPresenter;
import com.dong.starsmind.bookshelf.view.BookshelfView;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

import java.util.List;

/**
 * Created by zengwendong on 16/11/3.
 */
public class BookshelfActivity extends BaseActivity implements BookshelfView {

    private FloatingActionButton fab_add;
    private LoadMoreRecyclerView rv_bookshelf;
    private BookshelfAdapter bookshelfAdapter;
    private BookshelfPresenter bookshelfPresenter;
    private int space = AppUtils.dip2px(5);

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
        fab_add.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.colorPrimary)));
        rv_bookshelf = (LoadMoreRecyclerView) findViewById(R.id.rv_bookshelf);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_bookshelf.setLayoutManager(gridLayoutManager);
        rv_bookshelf.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = space;
                outRect.left = space;
                outRect.right = space;
            }
        });
        bookshelfAdapter = new BookshelfAdapter(this);
        rv_bookshelf.setAdapter(bookshelfAdapter);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "test";
                String path = Environment.getExternalStorageDirectory().getPath() + "/test.txt";
                bookshelfPresenter.addBook(name, path);
            }
        });

        bookshelfPresenter = new BookshelfPresenter(this);
        bookshelfPresenter.loadBookshelf();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, BookshelfActivity.class));
    }

    @Override
    public void addBookSuccess() {
        bookshelfPresenter.loadBookshelf();
    }

    @Override
    public void refreshData(List<Book> bookList) {
        bookshelfAdapter.setData(bookList);
    }
}
