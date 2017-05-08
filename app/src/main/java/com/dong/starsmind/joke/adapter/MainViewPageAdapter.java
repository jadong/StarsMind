package com.dong.starsmind.joke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dong.starsmind.home.MainFragment;
import com.dong.starsmind.joke.fragment.JokeImageFragment;
import com.dong.starsmind.joke.fragment.JokeTextFragment;

/**
 * Created by zengwendong on 16/12/5.
 */
public class MainViewPageAdapter extends FragmentPagerAdapter {

    public MainViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Fragment getItem(int position) {
        return new MainFragment();
        /*if (position == 0) {
            return new JokeTextFragment();
        } else {

            return new JokeImageFragment();
        }*/
    }

}
