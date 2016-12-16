package com.dong.starsmind.historyevent.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.historyevent.adapter.HistoryDetailAdapter;
import com.dong.starsmind.historyevent.entity.NodeDetail;
import com.dong.starsmind.historyevent.presenter.HistoryDetailPresenter;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

import java.util.List;

/**
 * 历史事件详情
 * Created by zengwendong on 16/12/16.
 */
public class HistoryDetailActivity extends BaseActivity implements RefreshListView<List<NodeDetail>> {

    private LoadMoreRecyclerView rv_history_detail;
    private HistoryDetailAdapter historyDetailAdapter;
    private HistoryDetailPresenter historyDetailPresenter;

    public static void startActivity(Context context,String eventId) {
        Intent intent = new Intent(context, HistoryDetailActivity.class);
        intent.putExtra("eventId",eventId);
        context.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected String getToolBarTitle() {
        return getString(R.string.event_detail);
    }

    @Override
    protected void initView() {
        rv_history_detail = (LoadMoreRecyclerView) findViewById(R.id.rv_history_detail);
        rv_history_detail.setLayoutManager(new LinearLayoutManager(this));
        historyDetailAdapter = new HistoryDetailAdapter(this);
        rv_history_detail.setAdapter(historyDetailAdapter);

        String eventId = getIntent().getStringExtra("eventId");
        historyDetailPresenter = new HistoryDetailPresenter(this);
        historyDetailPresenter.getHistoryEventDetail(eventId);
    }

    @Override
    public void refreshData(List<NodeDetail> nodeDetailList) {
        historyDetailAdapter.setData(nodeDetailList);
    }
}
