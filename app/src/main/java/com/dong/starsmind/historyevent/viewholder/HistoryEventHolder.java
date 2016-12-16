package com.dong.starsmind.historyevent.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.historyevent.activity.HistoryDetailActivity;
import com.dong.starsmind.historyevent.entity.HistoryEvent;

/**
 * Created by zengwendong on 16/12/15.
 */
public class HistoryEventHolder extends RecyclerView.ViewHolder {

    private TextView tv_event_title;
    private TextView tv_date;
    private String eventId;

    public HistoryEventHolder(final View itemView) {
        super(itemView);
        tv_event_title = (TextView) itemView.findViewById(R.id.tv_event_title);
        tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        itemView.findViewById(R.id.card_view_his_evt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryDetailActivity.startActivity(itemView.getContext(),eventId);
            }
        });
    }

    public void initData(HistoryEvent historyEvent) {
        this.eventId = historyEvent.getE_id();
        tv_event_title.setText(historyEvent.getTitle());
        tv_date.setText(historyEvent.getDate());
    }
}
