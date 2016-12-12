package com.dong.starsmind.news.view;

import com.dong.starsmind.joke.entity.Joke;

import java.util.List;

/**
 * Created by zengwendong on 16/12/5.
 */
public interface JokeListView{

    void refreshData(boolean isByTime,List<Joke> list);

    void showError(String msg);

}
