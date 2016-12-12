package com.dong.starsmind.news.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.news.adapter.NewsAdapter;
import com.dong.starsmind.news.entity.News;
import com.dong.starsmind.news.presenter.HealthNewsPresenter;
import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

import java.util.List;

/**
 * 健康资讯
 * Created by zengwendong on 16/11/22.
 */
public class HealthNewsActivity extends BaseActivity implements RefreshListView<List<News>>{

    private LoadMoreRecyclerView rv_gallery;

    private NewsAdapter newsAdapter;
    private HealthNewsPresenter healthNewsPresenter;

    private int space = AppUtils.dip2px(5);
    private int pageNo = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_news;
    }

    @Override
    protected String getToolBarTitle() {
        return getString(R.string.health_title);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, HealthNewsActivity.class));
    }

    @Override
    protected void initView() {
        healthNewsPresenter = new HealthNewsPresenter(this);
        rv_gallery = (LoadMoreRecyclerView) findViewById(R.id.rv_gallery);
        rv_gallery.setLayoutManager(new LinearLayoutManager(this));
        /*rv_gallery.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = space;
                outRect.left = space;
                outRect.right = space;
            }
        });*/
        rv_gallery.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageNo++;
                loadData();
            }
        });

        setAdapter();
        loadData();
    }

    private void setAdapter() {
        newsAdapter = new NewsAdapter(this);
        rv_gallery.setAdapter(newsAdapter);
        /*gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (newsAdapter.getItemViewType(position)) {
                    case RecyclerViewAdapter.ITEM_TYPE_FOOTER:
                        return 2;
                    default:
                        return 1;
                }
            }
        });*/
    }

    private void loadData() {
        healthNewsPresenter.getMeiNvImages(pageNo);
    }

    @Override
    public void refreshData(List<News> newsList) {
        newsAdapter.addData(newsList);
        rv_gallery.notifyMoreFinish(true);
    }
}
