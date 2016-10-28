package com.dong.starsmind.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengwendong on 16/10/28.
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> dataList;
    protected Context context;
    protected LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        dataList = new ArrayList<>();
    }

    public void setData(List<T> newList) {
        this.dataList.clear();
        this.dataList.addAll(newList);
    }

    public void addData(List<T> newList) {
        if (newList != null) {
            this.dataList.addAll(newList);
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

}
