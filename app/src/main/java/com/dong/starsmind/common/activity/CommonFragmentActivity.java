package com.dong.starsmind.common.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;

/**
 * Created by zengwendong on 16/12/18.
 */
public class CommonFragmentActivity extends BaseActivity {

    private static Class clazz;

    public static void startActivity(Context context,Class clazz, String title) {
        CommonFragmentActivity.clazz = clazz;
        Intent intent = new Intent(context, CommonFragmentActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_common_fragment;
    }

    @Override
    protected String getToolBarTitle() {
        return getIntent().getStringExtra("title");
    }

    @Override
    protected void initView() {
        try {
            if (clazz != null) {
                Object o = clazz.newInstance();
                Fragment fragment = (Fragment) o;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragTransaction = fm.beginTransaction();
                fragTransaction.add(R.id.fl_content, fragment);
                fragTransaction.commitAllowingStateLoss();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
