package com.dong.starsmind.bookshelf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.base.RecyclerViewAdapter;
import com.dong.starsmind.bookshelf.entity.Book;
import com.dong.starsmind.bookshelf.viewholder.BookViewHolder;

/**
 * Created by zengwendong on 16/11/3.
 */
public class BookshelfAdapter extends RecyclerViewAdapter<Book> {

    public BookshelfAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.layout_book_item, null);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookViewHolder bookViewHolder = (BookViewHolder) holder;
        bookViewHolder.setData(getItem(position));
    }

}
