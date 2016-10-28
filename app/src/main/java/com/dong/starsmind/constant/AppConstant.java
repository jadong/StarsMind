package com.dong.starsmind.constant;

import android.support.annotation.IntDef;

/**
 * Created by zengwendong on 16/10/28.
 */
public class AppConstant {

    public final static int STATUS_LOADING = 1;//加载中
    public final static int STATUS_LOAD_END = 2;//加载完成
    public final static int STATUS_LOAD_ERROR = 3;//加载失败

    @IntDef({STATUS_LOADING,STATUS_LOAD_END,STATUS_LOAD_ERROR})
    public @interface LoadStatus {
    }

}
