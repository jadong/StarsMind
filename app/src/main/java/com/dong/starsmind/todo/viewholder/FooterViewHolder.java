package com.dong.starsmind.todo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.constant.AppConstant;
import com.dong.starsmind.listener.ReloadListener;

/**
 * Created by zengwendong on 16/10/28.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_tip;//提示文案
    private ProgressBar refresh_progress;
    private int loadStatus = -1;

    private ReloadListener reloadListener;

    public FooterViewHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.layout_load_more, parent, false));
    }

    public FooterViewHolder(View itemView) {
        super(itemView);
        this.tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
        this.refresh_progress = (ProgressBar) itemView.findViewById(R.id.refresh_progress);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reloadListener != null) {
                    reloadListener.reload();
                }
            }
        });
    }

    public void setLoadStatus(@AppConstant.LoadStatus int loadStatus){
        this.loadStatus = loadStatus;
        if (loadStatus == AppConstant.STATUS_LOADING) {
            loading();
        } else if (loadStatus == AppConstant.STATUS_LOAD_END) {
            loadEnd();
        } else if (loadStatus == AppConstant.STATUS_LOAD_ERROR) {
            loadError();
        } else if (loadStatus == AppConstant.STATUS_LOAD_NO_DATA) {
            loadNoData();
        }else {
            hideFooterView();
        }
    }

    public void loading() {
        tv_tip.setText(itemView.getContext().getString(R.string.txt_loading));
        tv_tip.setVisibility(View.VISIBLE);
        refresh_progress.setVisibility(View.VISIBLE);
    }

    public void loadEnd() {
        tv_tip.setText(itemView.getContext().getString(R.string.txt_load_end));
        tv_tip.setVisibility(View.VISIBLE);
        refresh_progress.setVisibility(View.GONE);
    }

    public void loadError() {
        tv_tip.setText(itemView.getContext().getString(R.string.txt_load_error));
        tv_tip.setVisibility(View.VISIBLE);
        refresh_progress.setVisibility(View.GONE);
    }

    public void loadNoData() {
        tv_tip.setText(itemView.getContext().getString(R.string.txt_load_no_data));
        tv_tip.setVisibility(View.VISIBLE);
        refresh_progress.setVisibility(View.GONE);
    }

    public void hideFooterView(){
        itemView.setVisibility(View.GONE);
    }

    /**
     * 设置重新加载监听
     */
    public void setReloadListener(ReloadListener reloadListener){
        this.reloadListener = reloadListener;
    }

}
