package com.dong.starsmind.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.dong.starsmind.common.viewholder.FooterViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengwendong on 16/10/28.
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_FOOTER = 99;
    protected List<T> dataList;
    protected Context context;
    protected LayoutInflater layoutInflater;

    //加载状态
    protected int loadStatus = FooterViewHolder.STATUS_LOADING;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        dataList = new ArrayList<>();
    }

    public Context getContext() {
        return context;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setData(List<T> newList) {
        this.dataList.clear();
        addData(newList);
    }

    public void addData(List<T> newList) {
        if (newList != null) {
            this.dataList.addAll(newList);
            notifyDataSetChanged();
        }
    }

    public void clearData() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public void addDataToFirst(List<T> newList) {
        if (newList != null) {
            this.dataList.addAll(0, newList);
            notifyDataSetChanged();
        }
    }

    public void addOneData(T data) {
        if (data != null) {
            this.dataList.add(data);
            notifyDataSetChanged();
        }
    }

    public int getDataCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public T getItem(int position) {
        if (position < getDataCount()) {
            return dataList.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return getDataCount();
    }

    /**
     * 设置脚布局的加载状态 FooterViewHolder.STATUS
     *
     * @param loadStatus FooterViewHolder.STATUS
     */
    public void setFooterViewStatus(@FooterViewHolder.LoadStatus int loadStatus) {
        this.loadStatus = loadStatus;
    }
}
