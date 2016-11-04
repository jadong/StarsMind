package com.dong.starsmind.bookshelf.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.bookshelf.entity.Book;

/**
 * Created by zengwendong on 16/11/3.
 */
public class BookViewHolder extends RecyclerView.ViewHolder{

    private TextView tv_name;

    public BookViewHolder(View itemView) {
        super(itemView);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
    }

    public void setData(Book book){
        if (book == null) {
            return;
        }
        tv_name.setText(book.getName());
    }
}
