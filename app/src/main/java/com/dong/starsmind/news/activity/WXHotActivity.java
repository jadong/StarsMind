package com.dong.starsmind.news.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.common.viewholder.FooterViewHolder;
import com.dong.starsmind.news.view.RefreshNewsListView;
import com.dong.starsmind.news.adapter.NewsAdapter;
import com.dong.starsmind.news.entity.News;
import com.dong.starsmind.news.presenter.WXHotPresenter;
import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

import java.util.List;

/**
 * 微信精选
 * Created by zengwendong on 16/11/21.
 */
public class WXHotActivity extends BaseActivity implements RefreshNewsListView {

    private LoadMoreRecyclerView rv_information;

    private WXHotPresenter wxHotPresenter;
    private NewsAdapter newsAdapter;
    private int pageNo = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_wxhot;
    }

    @Override
    protected String getToolBarTitle() {
        return getString(R.string.wxhot_title);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, WXHotActivity.class));
    }

    @Override
    protected void initView() {
        wxHotPresenter = new WXHotPresenter(this);
        rv_information = (LoadMoreRecyclerView) findViewById(R.id.rv_information);
        rv_information.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this);
        rv_information.setAdapter(newsAdapter);

        rv_information.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageNo++;
                loadData();
            }
        });

        loadData();
    }

    private void loadData() {
        wxHotPresenter.getWXInformation("", pageNo);
    }

    @Override
    public void refreshData(List<News> newsList) {
        boolean hasMore = newsList != null && newsList.size() >= 10;//判断是否还有下页数据(api没有返回页码,暂时这样实现)
        if (pageNo == 1) {
            if (hasMore) {
                newsAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOADING);
            } else {
                newsAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOAD_END);
            }

            newsAdapter.setData(newsList);

        } else {
            if (hasMore) {
                newsAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOADING);
            } else {
                newsAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOAD_END);
            }
            newsAdapter.addData(newsList);
        }
    }

    @Override
    public void noContentFound(String msg) {
        newsAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOAD_NO_DATA);
        newsAdapter.clearData();
        AppUtils.toastShort(msg);
    }
}
