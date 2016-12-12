package com.dong.starsmind.common.listener;

import android.support.v7.widget.RecyclerView;

import com.dong.starsmind.app.SMLog;

/**
 * Created by zengwendong on 16/12/1.
 */
public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private int scrollDistance = 10;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        boolean isSignificantDelta = Math.abs(dy) > scrollDistance;
        if (isSignificantDelta) {
            SMLog.i("RecyclerViewScrollListener", "scrollDistance---->" + dy);
        }
    }

}
