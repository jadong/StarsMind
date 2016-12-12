package com.dong.starsmind.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.base.RecyclerViewAdapter;
import com.dong.starsmind.common.viewholder.FooterViewHolder;
import com.dong.starsmind.joke.entity.Joke;
import com.dong.starsmind.news.viewholder.JokeViewHolder;

/**
 * Created by zengwendong on 16/11/21.
 */
public class JokeAdapter extends RecyclerViewAdapter<Joke> {

    private static final int ITEM_TYPE_ITEM = 2;

    public JokeAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_TYPE_FOOTER) {
            view = layoutInflater.inflate(R.layout.layout_load_more, parent, false);
            return new FooterViewHolder(view);
        } else if (viewType == ITEM_TYPE_ITEM) {
            view = layoutInflater.inflate(R.layout.layout_joke, parent, false);
            return new JokeViewHolder(view);
        }

        throw new RuntimeException("无效的viewType:" + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JokeViewHolder) {
            JokeViewHolder jokeViewHolder = (JokeViewHolder) holder;
            jokeViewHolder.bindData(getItem(position));
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.setLoadStatus(loadStatus);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int footerPosition = getItemCount() - 1;
        if (position == footerPosition) {
            return ITEM_TYPE_FOOTER;
        }
        return ITEM_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        int count = getDataCount();
        count++;//底部
        return count;
    }

    public Joke getFirstItem(){
        if(getDataCount() > 0){
            return getItem(0);
        }
        return null;
    }
}
