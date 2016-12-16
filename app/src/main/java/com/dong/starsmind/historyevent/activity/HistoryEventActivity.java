package com.dong.starsmind.historyevent.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.historyevent.adapter.HistoryEventAdapter;
import com.dong.starsmind.historyevent.entity.HistoryEvent;
import com.dong.starsmind.historyevent.presenter.HistoryEventPresenter;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

import java.util.Calendar;
import java.util.List;

/**
 * 今日历史事件
 * Created by zengwendong on 16/12/15.
 */
public class HistoryEventActivity extends BaseActivity implements RefreshListView<List<HistoryEvent>> {

    private LoadMoreRecyclerView rv_history_event;
    private HistoryEventAdapter historyEventAdapter;
    private HistoryEventPresenter historyEventPresenter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, HistoryEventActivity.class));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_history_event;
    }

    @Override
    protected String getToolBarTitle() {
        return getString(R.string.today_history_event);
    }

    @Override
    protected void initView() {
        rv_history_event = (LoadMoreRecyclerView) findViewById(R.id.rv_history_event);
        rv_history_event.setLayoutManager(new LinearLayoutManager(this));
        historyEventAdapter = new HistoryEventAdapter(this);
        rv_history_event.setAdapter(historyEventAdapter);

        historyEventPresenter = new HistoryEventPresenter(this);

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String todayDate = month + "/" + day;
        historyEventPresenter.getHistoryEvent(todayDate);
    }

    @Override
    public void refreshData(List<HistoryEvent> historyEvents) {
        historyEventAdapter.setData(historyEvents);
    }
}
