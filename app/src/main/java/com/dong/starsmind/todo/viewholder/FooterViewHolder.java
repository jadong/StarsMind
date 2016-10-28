package com.dong.starsmind.todo.viewholder;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dong.starsmind.R;

/**
 * Created by zengwendong on 16/10/28.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_tip;//提示文案
    private ProgressBar refresh_progress;

    public FooterViewHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.layout_load_more, parent, false));
    }

    public FooterViewHolder(View itemView) {
        super(itemView);
        this.tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
        this.refresh_progress = (ProgressBar) itemView.findViewById(R.id.refresh_progress);
    }

    public void isLoading() {
        tv_tip.setText(itemView.getContext().getString(R.string.txt_loading));
        tv_tip.setVisibility(View.VISIBLE);
        refresh_progress.setVisibility(View.VISIBLE);
    }

    public void isLoadEnd() {
        tv_tip.setText(itemView.getContext().getString(R.string.txt_load_end));
        tv_tip.setVisibility(View.VISIBLE);
        refresh_progress.setVisibility(View.GONE);
    }

    public void setFooterViewText(@StringRes int resId) {
        tv_tip.setText(itemView.getContext().getString(resId));
        tv_tip.setVisibility(View.VISIBLE);
        refresh_progress.setVisibility(View.GONE);
    }

    public void setFooterViewText(String tips){
        tv_tip.setText(tips);
        tv_tip.setVisibility(View.VISIBLE);
        refresh_progress.setVisibility(View.GONE);
    }

}
