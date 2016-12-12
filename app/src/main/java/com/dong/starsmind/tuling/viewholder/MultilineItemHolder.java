package com.dong.starsmind.tuling.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dong.starsmind.R;
import com.dong.starsmind.common.activity.WebViewActivity;
import com.dong.starsmind.tuling.entity.TLInfo;
import com.dong.starsmind.tuling.entity.TuLingData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengwendong on 16/11/24.
 */
public class MultilineItemHolder extends RecyclerView.ViewHolder {

    private LinearLayout ll_multiline_item;

    private List<View> itemViews = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public MultilineItemHolder(View itemView) {
        super(itemView);
        layoutInflater = LayoutInflater.from(itemView.getContext());
        ll_multiline_item = (LinearLayout) itemView.findViewById(R.id.ll_multiline_item);
        initItemView();
    }

    public void bindData(TuLingData tuLingData) {
        List<TLInfo> list = tuLingData.getList();
        if (list == null) {
            return;
        }
        int childCount = ll_multiline_item.getChildCount();
        if (childCount == 0) {
            int size = list.size() > itemViews.size() ? itemViews.size() : list.size();
            for (int i = 0; i < size; i++) {
                View view = getView(i, size, list.get(i));
                ll_multiline_item.addView(view);
            }
        } else {
            int size = list.size() > itemViews.size() ? itemViews.size() : list.size();
            for (int i = 0; i < size; i++) {
                getView(i, size, list.get(i));
            }
        }
    }

    @NonNull
    private View getView(int i, int size, final TLInfo tlInfo) {
        View view = itemViews.get(i);
        TextView textView = (TextView) view.findViewById(R.id.tv_text);
        final String text = TextUtils.isEmpty(tlInfo.getArticle()) ? tlInfo.getName() : tlInfo.getArticle();
        textView.setText(text);
        textView.setTextColor(itemView.getResources().getColor(R.color.color_666));
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
        if (TextUtils.isEmpty(tlInfo.getIcon())) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(itemView.getContext()).load(tlInfo.getIcon()).into(imageView);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.startActivity(itemView.getContext(), text, tlInfo.getDetailurl());
            }
        });

        View v_line = view.findViewById(R.id.v_line);
        if (i == size - 1) {
            v_line.setVisibility(View.GONE);
        } else {
            v_line.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void initItemView() {
        for (int i = 0; i < 5; i++) {
            View view = layoutInflater.inflate(R.layout.layout_msg_single, null);
            itemViews.add(view);
        }
    }

}
