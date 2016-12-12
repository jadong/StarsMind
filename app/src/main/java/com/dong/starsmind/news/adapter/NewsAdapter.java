package com.dong.starsmind.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.base.RecyclerViewAdapter;
import com.dong.starsmind.common.viewholder.FooterViewHolder;
import com.dong.starsmind.news.entity.News;
import com.dong.starsmind.news.viewholder.WXHotViewHolder;

/**
 * Created by zengwendong on 16/11/21.
 */
public class NewsAdapter extends RecyclerViewAdapter<News> {

    private static final int ITEM_TYPE_ITEM = 2;

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_TYPE_FOOTER) {
            view = layoutInflater.inflate(R.layout.layout_load_more, parent, false);
            return new FooterViewHolder(view);
        } else if (viewType == ITEM_TYPE_ITEM) {
            view = layoutInflater.inflate(R.layout.layout_news_item, parent, false);
            return new WXHotViewHolder(view);
        }

        throw new RuntimeException("无效的viewType:" + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WXHotViewHolder) {
            WXHotViewHolder wxHotViewHolder = (WXHotViewHolder) holder;
            wxHotViewHolder.bindData(getItem(position));
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
}
