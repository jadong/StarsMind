package com.dong.starsmind.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dong.starsmind.R;

/**
 * Created by zengwendong on 16/12/5.
 */
public class PullDownHeaderView extends FrameLayout {

    public PullDownHeaderView(Context context) {
        super(context);
        init(context);
    }

    public PullDownHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullDownHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private TextView tv_tip;
    private ProgressBar refresh_progress;

    public void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pull_down_header, null);
        addView(view);

        tv_tip = (TextView) view.findViewById(R.id.tv_tip);
        refresh_progress = (ProgressBar) view.findViewById(R.id.refresh_progress);
    }

    /**
     * 正在刷新
     */
    public void setRefreshing(){
        refresh_progress.setVisibility(VISIBLE);
        tv_tip.setText(getResources().getString(R.string.txt_refreshing));
    }

    /**
     * 下拉刷新
     */
    public void setPullRefresh(){
        refresh_progress.setVisibility(GONE);
        tv_tip.setText(getResources().getString(R.string.txt_pull_refresh));
    }

    /**
     * 松开刷新
     */
    public void setReleaseRefresh(){
        refresh_progress.setVisibility(GONE);
        tv_tip.setText(getResources().getString(R.string.txt_release_refresh));
    }

}
