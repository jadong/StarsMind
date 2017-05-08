package com.dong.starsmind.joke.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.app.SMLog;
import com.dong.starsmind.common.viewholder.FooterViewHolder;
import com.dong.starsmind.joke.adapter.JokeAdapter;
import com.dong.starsmind.joke.entity.Joke;
import com.dong.starsmind.joke.presenter.JokePresenter;
import com.dong.starsmind.news.view.JokeListView;
import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;
import com.dong.starsmind.widgets.PullDownHeaderView;
import com.dong.starsmind.widgets.SuperSwipeRefreshLayout;

import java.util.List;

/**
 * Created by zengwendong on 16/12/5.
 */
public class JokeTextFragment extends Fragment implements JokeListView {

    private SuperSwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreRecyclerView rv_joke_list;
    private FloatingActionButton fab_up;

    private JokePresenter jokePresenter;
    private JokeAdapter jokeAdapter;
    private int pageNo = 1;//页码
    private int scrollY = 0;
    private int screen_height = 0 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_text, null);
        fab_up = (FloatingActionButton) view.findViewById(R.id.fab_up);
        fab_up.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.orange)));
        swipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        rv_joke_list = (LoadMoreRecyclerView) view.findViewById(R.id.rv_joke_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_joke_list.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        screen_height = AppUtils.getScreenHeight();
        jokePresenter = new JokePresenter(this);

        setAdapter();
        setListener();

        loadData();
    }

    private void setAdapter() {
        jokeAdapter = new JokeAdapter(getActivity());
        rv_joke_list.setAdapter(jokeAdapter);
    }

    private void setListener() {
        rv_joke_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scrollY += dy;
                fab_up.setVisibility(scrollY > screen_height ? View.VISIBLE : View.GONE);
                SMLog.i("JokeTextFragment", "RecyclerView---scrollY-->=" + scrollY);
            }
        });

        final PullDownHeaderView headerView = new PullDownHeaderView(getActivity());
        swipeRefreshLayout.setHeaderView(headerView);
        swipeRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                headerView.setRefreshing();
                Joke item = jokeAdapter.getFirstItem();
                if (item != null) {
                    jokePresenter.getJokeByTime("text", item.getUnixtime());
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                if (enable) {
                    headerView.setReleaseRefresh();
                } else {
                    headerView.setPullRefresh();
                }
            }
        });

        rv_joke_list.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageNo++;
                loadData();
            }
        });

        fab_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollY = 0;
                rv_joke_list.scrollToPosition(0);
            }
        });
    }

    private void loadData() {
        jokePresenter.getJokeList("text", pageNo);
    }

    @Override
    public void refreshData(boolean isByTime, List<Joke> jokeList) {
        if (isByTime) {//如果按时间更新,则加入启始位置
            jokeAdapter.addDataToFirst(jokeList);
            return;
        }

        boolean hasMore = jokeList != null && jokeList.size() >= 20;//判断是否还有下页数据(api没有返回页码,暂时这样实现)
        if (pageNo == 1) {
            if (hasMore) {
                jokeAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOADING);
            } else {
                jokeAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOAD_END);
            }

            jokeAdapter.setData(jokeList);

        } else {
            if (hasMore) {
                jokeAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOADING);
            } else {
                jokeAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOAD_END);
            }
            jokeAdapter.addData(jokeList);
        }

        rv_joke_list.notifyMoreFinish(hasMore);
    }

    @Override
    public void showError(String msg) {
        jokeAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOAD_NO_DATA);
        jokeAdapter.clearData();
        AppUtils.toastShort(msg);
    }
}
