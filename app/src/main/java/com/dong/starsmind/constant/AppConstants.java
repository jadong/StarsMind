package com.dong.starsmind.constant;

import android.support.annotation.IntDef;

/**
 * Created by zengwendong on 16/10/28.
 */
public class AppConstants {

    public static final String baidu_apikey = "baidu_apikey:b5de3288a03dbc144f6bf23af8b69fea";
    public static final String tuling_apiKey = "5a4b4a5ff4784cb1b3e4b2ddbf89b3d1";
    public static final String tuling_secret = "dde2341c8a22eeea";
    public static final String city = "北京市";


    //---------------------------------------------------------------
    public final static int STATUS_CODE_0 = 0;//成功
    public final static int STATUS_CODE_1 = 1;//失败

    @IntDef({STATUS_CODE_0, STATUS_CODE_1})
    public @interface StatusCode {
    }

}
