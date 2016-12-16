package com.dong.starsmind.historyevent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.base.RecyclerViewAdapter;
import com.dong.starsmind.historyevent.entity.NodeDetail;
import com.dong.starsmind.historyevent.viewholder.HistoryDetailHolder;

/**
 * Created by zengwendong on 16/12/16.
 */
public class HistoryDetailAdapter extends RecyclerViewAdapter<NodeDetail> {

    public HistoryDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.layout_history_detail_item,parent,false);
        return new HistoryDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HistoryDetailHolder) {
            HistoryDetailHolder historyDetailHolder = (HistoryDetailHolder) holder;
            historyDetailHolder.initData(getItem(position));
        }
    }
}
