package com.dong.starsmind.common.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.utils.ImageLoadUtil;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zengwendong on 16/12/6.
 */
public class BigImageActivity extends BaseActivity implements ImageLoadUtil.LoadListener {

    private PhotoView iv_image;
    private TextView tv_content;
    private ProgressBar progressBar;
    private RelativeLayout rl_title_layout;
    private ImageView iv_back;
    private int view_height = AppUtils.dip2px(50);
    private boolean isShow = true;

    @Override
    protected int getContentView() {
        return R.layout.activity_big_image;
    }

    @Override
    protected String getToolBarTitle() {
        return "";
    }

    protected boolean getEnableSwipeBack() {
        return false;
    }

    public static void startActivity(Context context, String url, String content) {
        Intent intent = new Intent(context, BigImageActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        rl_title_layout = (RelativeLayout) findViewById(R.id.rl_title_layout);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_image = (PhotoView) findViewById(R.id.iv_image);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_content = (TextView) findViewById(R.id.tv_content);
        iv_image.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                ObjectAnimator.ofFloat(tv_content, "translationY", isShow ? 0 : view_height, isShow ? view_height : 0).setDuration(500).start();
                ObjectAnimator.ofFloat(rl_title_layout, "translationY", isShow ? 0 : -view_height, isShow ? -view_height : 0).setDuration(500).start();
                isShow = !isShow;
            }
        });

        String url = getIntent().getStringExtra("url");
        String content = getIntent().getStringExtra("content");
        if (url.contains(".gif")) {
            ImageLoadUtil.loadGif(this, url, iv_image, this);
        } else {
            ImageLoadUtil.load(this, url, iv_image, this);
        }
        tv_content.setText(content);

        setListener();

    }

    private void setListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onFailed() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);
    }
}
