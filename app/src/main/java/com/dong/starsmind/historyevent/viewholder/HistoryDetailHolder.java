package com.dong.starsmind.historyevent.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.historyevent.entity.NodeDetail;
import com.dong.starsmind.utils.ImageLoadUtil;

/**
 * Created by zengwendong on 16/12/16.
 */
public class HistoryDetailHolder extends RecyclerView.ViewHolder {

    private TextView tv_text;
    private ImageView iv_image;

    public HistoryDetailHolder(View itemView) {
        super(itemView);
        tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
    }

    public void initData(NodeDetail nodeDetail) {
        if (TextUtils.isEmpty(nodeDetail.getText())) {
            tv_text.setVisibility(View.GONE);
        } else {
            tv_text.setText(nodeDetail.getText());
        }
        if (nodeDetail.getType() == 3) {
            ImageLoadUtil.load(itemView.getContext(), nodeDetail.getPicUrl(), iv_image);
            iv_image.setVisibility(View.VISIBLE);
        }
    }
}
