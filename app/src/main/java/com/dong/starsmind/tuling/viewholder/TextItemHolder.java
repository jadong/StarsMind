package com.dong.starsmind.tuling.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.common.activity.WebViewActivity;
import com.dong.starsmind.tuling.entity.MessageEntity;
import com.dong.starsmind.tuling.entity.TuLingData;
import com.dong.starsmind.utils.AppUtils;

/**
 * Created by zengwendong on 16/11/24.
 */
public class TextItemHolder extends RecyclerView.ViewHolder {

    private FrameLayout fl_item_view;

    private TextView tv_text;

    public TextItemHolder(View itemView) {
        super(itemView);
        tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        fl_item_view = (FrameLayout) itemView.findViewById(R.id.fl_item_view);
    }

    public void bindData(MessageEntity messageEntity) {

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fl_item_view.getLayoutParams();
        int resid = 0;
        if (messageEntity instanceof TuLingData) {
            TuLingData tuLingData = (TuLingData) messageEntity;
            String text = tuLingData.getText();
            String url = tuLingData.getUrl();
            if (!TextUtils.isEmpty(url)) {
                String urlText = "点击查看";
                SpannableStringBuilder builder = new SpannableStringBuilder(text + "\n" + urlText);

                int start = text.length();
                int end = text.length() + urlText.length() + 1;
                builder.setSpan(new UrlClickSpan(text, url), start, end, Spanned.SPAN_MARK_MARK);
                //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
                ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
                builder.setSpan(blueSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_text.setMovementMethod(LinkMovementMethod.getInstance());
                tv_text.setText(builder);
            } else {
                tv_text.setText(text);
            }

            layoutParams.gravity = Gravity.LEFT;
            layoutParams.rightMargin = AppUtils.dip2px(40);
            layoutParams.leftMargin = 0;
            resid = R.drawable.left_chat_item;
            tv_text.setTextColor(itemView.getResources().getColor(R.color.color_666));

        } else {
            String text = messageEntity.getMyMessage();
            tv_text.setText(text);

            layoutParams.gravity = Gravity.RIGHT;
            layoutParams.leftMargin = AppUtils.dip2px(40);
            layoutParams.rightMargin = 0;
            resid = R.drawable.right_chat_item;
            tv_text.setTextColor(itemView.getResources().getColor(R.color.white));
        }

        fl_item_view.setBackgroundResource(resid);
        fl_item_view.setLayoutParams(layoutParams);

    }

    private class UrlClickSpan extends ClickableSpan {

        private String url;
        private String title;

        public UrlClickSpan(String title, String url) {
            this.url = url;
            this.title = title;
        }

        @Override
        public void onClick(View widget) {
            WebViewActivity.startActivity(itemView.getContext(), title, url);
            /*Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            itemView.getContext().startActivity(intent);*/
        }
    }
}
