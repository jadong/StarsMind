package com.dong.starsmind.news.view;

import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.news.entity.News;

import java.util.List;

/**
 * Created by zengwendong on 16/12/1.
 */
public interface RefreshNewsListView extends RefreshListView<List<News>> {

    void noContentFound(String msg);

}
