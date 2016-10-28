package com.dong.starsmind.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dong.starsmind.R;

/**
 * Created by zengwendong on 16/7/20.
 */
public abstract class BaseActivity extends SwipeBackActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnableSwipeBack(getEnableSwipeBack());
        setContentView(getContentView());
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);//默认从左边滑动退出
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(getToolBarTitle());
            toolbar.setNavigationIcon(R.mipmap.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        initView();

    }

    /**
     * 默认启用滑动退出,如果要禁用则子类实现该方法返回false即可
     */
    protected boolean getEnableSwipeBack(){
        return true;
    }

    @LayoutRes
    protected abstract int getContentView();

    protected abstract String getToolBarTitle();

    protected abstract void initView();


}
