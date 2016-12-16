package com.dong.starsmind.historyevent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.base.RecyclerViewAdapter;
import com.dong.starsmind.historyevent.entity.HistoryEvent;
import com.dong.starsmind.historyevent.viewholder.HistoryEventHolder;

/**
 * Created by zengwendong on 16/12/15.
 */
public class HistoryEventAdapter extends RecyclerViewAdapter<HistoryEvent> {

    public HistoryEventAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.layout_history_event_item, parent, false);
        return new HistoryEventHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HistoryEventHolder) {
            HistoryEventHolder historyEventHolder = (HistoryEventHolder) holder;
            historyEventHolder.initData(getItem(position));
        }
    }

}
