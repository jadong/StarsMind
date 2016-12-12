package com.dong.starsmind.news.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dong.starsmind.R;
import com.dong.starsmind.common.activity.WebViewActivity;
import com.dong.starsmind.news.entity.News;

/**
 * Created by zengwendong on 16/11/21.
 */
public class WXHotViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_title;
    private TextView tv_time;
    private ImageView iv_cover;
    private TextView tv_description;
    private String title;
    private String web_url;

    public WXHotViewHolder(final View itemView) {
        super(itemView);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
        tv_description = (TextView) itemView.findViewById(R.id.tv_description);
        itemView.findViewById(R.id.card_view_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.startActivity(itemView.getContext(), title, web_url);
            }
        });
    }

    public void bindData(News news) {
        if (news == null) {
            return;
        }
        web_url = news.getUrl();
        title = news.getTitle();
        tv_title.setText(title);
        tv_time.setText(news.getCtime());
        tv_description.setText(news.getDescription());

        Glide.with(itemView.getContext()).load(news.getPicUrl()).into(iv_cover);
    }
}
