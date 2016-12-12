package com.dong.starsmind.todo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.base.RecyclerViewAdapter;
import com.dong.starsmind.common.viewholder.FooterViewHolder;
import com.dong.starsmind.helper.OnMoveAndSwipedListener;
import com.dong.starsmind.listener.ReloadListener;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.presenter.DeleteToDoPresenter;
import com.dong.starsmind.todo.view.TodoView;
import com.dong.starsmind.todo.viewholder.ToDoViewHolder;

import java.util.Collections;

/**
 * Created by zengwendong on 16/10/28.
 */
public class ToDoAdapter extends RecyclerViewAdapter<TODO> implements ReloadListener, OnMoveAndSwipedListener, TodoView {

    private static final int ITEM_TYPE_TODO = 2;

    private DeleteToDoPresenter deleteToDoPresenter;

    public ToDoAdapter(Context context) {
        super(context);
        deleteToDoPresenter = new DeleteToDoPresenter(this);
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
            if (getDataCount() == 0) {
                footerViewHolder.setLoadStatus(FooterViewHolder.STATUS_LOAD_HIDE);
            } else {
                footerViewHolder.setLoadStatus(loadStatus);
            }
            footerViewHolder.setReloadListener(this);//设置重新加载监听
        }

    }

    public void notifyFooterView() {
        notifyItemChanged(getItemCount() - 1);
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

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换dataList数据的位置
        Collections.swap(dataList, fromPosition, toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }


    @Override
    public void onItemDismiss(int position) {
        TODO todo = getItem(position);
        if (todo != null) {
            deleteToDoPresenter.deleteToDoBy(todo.getId(), position);
        }
    }

    @Override
    public void onSuccess(Object o) {
        try {
            int deletePos = (int) o;
            //删除RecyclerView列表对应item
            notifyItemRemoved(deletePos);
            //删除dataList数据
            dataList.remove(deletePos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
