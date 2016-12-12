package com.dong.starsmind.news.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dong.starsmind.R;
import com.dong.starsmind.common.activity.WebViewActivity;
import com.dong.starsmind.news.entity.News;

/**
 * Created by zengwendong on 16/11/21.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView iv_image;
    private String title;
    private String web_url;

    public ImageViewHolder(final View itemView) {
        super(itemView);
        iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        itemView.findViewById(R.id.card_view_image).setOnClickListener(new View.OnClickListener() {
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
        Glide.with(itemView.getContext()).load(news.getPicUrl()).into(iv_image);
    }
}
