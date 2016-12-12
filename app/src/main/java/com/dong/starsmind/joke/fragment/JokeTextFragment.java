package com.dong.starsmind.joke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.common.viewholder.FooterViewHolder;
import com.dong.starsmind.news.adapter.JokeAdapter;
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

    private JokePresenter jokePresenter;
    private JokeAdapter jokeAdapter;
    private int pageNo = 1;//页码

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_text,null);
        swipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        rv_joke_list = (LoadMoreRecyclerView) view.findViewById(R.id.rv_joke_list);
        rv_joke_list.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        final PullDownHeaderView headerView = new PullDownHeaderView(getActivity());
        swipeRefreshLayout.setHeaderView(headerView);
        swipeRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                headerView.setRefreshing();
                Joke item = jokeAdapter.getFirstItem();
                if (item != null) {
                    jokePresenter.getJokeByTime("text",item.getUnixtime());
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

    }

    private void loadData() {
        jokePresenter.getJokeList("text",pageNo);
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
