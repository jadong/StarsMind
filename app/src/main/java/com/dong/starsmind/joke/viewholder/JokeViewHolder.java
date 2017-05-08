package com.dong.starsmind.joke.viewholder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.common.activity.BigImageActivity;
import com.dong.starsmind.joke.entity.Joke;
import com.dong.starsmind.utils.ImageLoadUtil;

/**
 * Created by zengwendong on 16/11/21.
 */
public class JokeViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_content;
    private TextView tv_time;
    private ImageView iv_joke_image;
    private Joke joke;
    private ClipboardManager clipboardManager;
    private Context context;

    public JokeViewHolder(final View itemView) {
        super(itemView);
        context = itemView.getContext();
        clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        iv_joke_image = (ImageView) itemView.findViewById(R.id.iv_joke_image);
        itemView.findViewById(R.id.card_view_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(joke.getUrl())) {
                    BigImageActivity.startActivity(context, joke.getUrl(), joke.getContent());
                } else {
                    ClipData clipData;
                    String text = joke.getContent();
                    clipData = ClipData.newPlainText("text", text);
                    clipboardManager.setPrimaryClip(clipData);
                }
            }
        });
    }

    public void bindData(Joke joke) {
        if (joke == null) {
            return;
        }
        this.joke = joke;
        String text = joke.getContent().replaceAll("　　", "\r\n");
        tv_content.setText(text);
        tv_time.setText(joke.getUpdatetime());

        String url = joke.getUrl();
        if (!TextUtils.isEmpty(url)) {
            iv_joke_image.setVisibility(View.VISIBLE);
            if (url.contains(".gif")) {
                ImageLoadUtil.loadGif(itemView.getContext(), url, iv_joke_image);
            } else {
                ImageLoadUtil.load(itemView.getContext(), url, iv_joke_image);
            }
        }
    }
}
