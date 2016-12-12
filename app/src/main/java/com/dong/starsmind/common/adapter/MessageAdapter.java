package com.dong.starsmind.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.base.RecyclerViewAdapter;
import com.dong.starsmind.tuling.entity.MessageEntity;
import com.dong.starsmind.tuling.entity.TuLingData;
import com.dong.starsmind.tuling.viewholder.MultilineItemHolder;
import com.dong.starsmind.tuling.viewholder.TextItemHolder;

/**
 * Created by zengwendong on 16/11/24.
 */
public class MessageAdapter extends RecyclerViewAdapter<MessageEntity> {

    private int TYPE_TEXT_ITEM = 1;
    private int TYPE_MULTILINE_ITEM = 2;

    public MessageAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_MULTILINE_ITEM) {
            view = getLayoutInflater().inflate(R.layout.layout_multiline_item, parent, false);
            return new MultilineItemHolder(view);
        } else {
            view = getLayoutInflater().inflate(R.layout.layout_text_item, parent, false);
            return new TextItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageEntity entity = getItem(position);
        if (holder instanceof MultilineItemHolder) {
            TuLingData tuLingData = (TuLingData) entity;
            MultilineItemHolder multilineItemHolder = (MultilineItemHolder) holder;
            multilineItemHolder.bindData(tuLingData);
        } else {
            TextItemHolder textItemHolder = (TextItemHolder) holder;
            textItemHolder.bindData(entity);
        }
    }

    @Override
    public int getItemViewType(int position) {

        MessageEntity entity = getItem(position);
        if (entity != null) {
            if (entity instanceof TuLingData) {
                TuLingData tuLingData = (TuLingData) entity;
                if (tuLingData.getCode() == 302000 || tuLingData.getCode() == 308000) {
                    return TYPE_MULTILINE_ITEM;
                } else {
                    return TYPE_TEXT_ITEM;
                }
            } else {
                return TYPE_TEXT_ITEM;
            }

        }

        return super.getItemViewType(position);
    }
}
