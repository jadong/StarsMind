package com.dong.starsmind.read.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dong.starsmind.R;
import com.dong.starsmind.read.activity.ReadActivity;
import com.dong.starsmind.read.entity.Book;

/**
 * Created by zengwendong on 16/11/3.
 */
public class BookViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_name;
    private ImageView iv_cover;
    private Book book;

    public BookViewHolder(final View itemView) {
        super(itemView);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
        itemView.findViewById(R.id.card_view_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book != null) {
                    ReadActivity.startActivity(itemView.getContext(),book.getPath());
                }
            }
        });
    }

    public void setData(Book book) {
        if (book == null) {
            return;
        }
        this.book = book;
        tv_name.setText(book.getTitle());
        if (!TextUtils.isEmpty(book.getCover())) {
            Glide.with(itemView.getContext()).load(book.getCover()).into(iv_cover);
        }
    }
}
