package com.dong.starsmind.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dong.starsmind.R;
import com.dong.starsmind.widgets.SwipeBackLayout;

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

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (toolbar != null) {
            toolbar.setTitle(getToolBarTitle());
        }
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
