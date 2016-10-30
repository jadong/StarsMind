package com.dong.starsmind.todo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.base.RecyclerViewAdapter;
import com.dong.starsmind.constant.AppConstant;
import com.dong.starsmind.listener.ReloadListener;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.viewholder.FooterViewHolder;
import com.dong.starsmind.todo.viewholder.ToDoViewHolder;

/**
 * Created by zengwendong on 16/10/28.
 */
public class ToDoAdapter extends RecyclerViewAdapter<TODO> implements ReloadListener {

    private static final int ITEM_TYPE_FOOTER = 1;
    private static final int ITEM_TYPE_TODO = 2;

    //加载状态
    private int loadStatus = AppConstant.STATUS_LOADING;

    public ToDoAdapter(Context context) {
        super(context);
    }

    /**
     * 设置加载状态
     *
     * @param loadStatus AppConstant.STATUS
     */
    public void setLoadStatus(@AppConstant.LoadStatus int loadStatus) {
        this.loadStatus = loadStatus;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (viewType == ITEM_TYPE_TODO) {
            view = layoutInflater.inflate(R.layout.layout_todo_item, parent, false);
            return new ToDoViewHolder(view);
        } else if (viewType == ITEM_TYPE_FOOTER) {
            view = layoutInflater.inflate(R.layout.layout_load_more, parent, false);
            return new FooterViewHolder(view);
        }
        throw new RuntimeException("无效的viewType:" + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ToDoViewHolder) {
            ToDoViewHolder toDoViewHolder = (ToDoViewHolder) holder;
            TODO todo = getItem(position);
            toDoViewHolder.setData(todo);
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.setReloadListener(this);//设置重新加载监听
        }

    }

    @Override
    public int getItemViewType(int position) {
        int footerPosition = getItemCount() - 1;
        if (position == footerPosition) {
            return ITEM_TYPE_FOOTER;
        }
        return ITEM_TYPE_TODO;
    }

    @Override
    public int getItemCount() {
        int count = getDataCount();
        count++;//底部
        return count;
    }

    /**
     * 重新加载数据
     */
    @Override
    public void reload() {

    }
}
