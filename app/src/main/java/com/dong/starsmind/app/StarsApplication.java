package com.dong.starsmind.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by zengwendong on 16/10/26.
 */
public class StarsApplication extends MultiDexApplication {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
