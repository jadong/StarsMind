package com.dong.starsmind.app;

import android.util.Log;

import com.dong.starsmind.BuildConfig;

/**
 * Created by zengwendong on 16/11/21.
 */
public class SMLog {

    public static boolean isDebug = BuildConfig.LOG_DEBUG;
    public static boolean isSystemOut = false;

    public static void i(String tag, String msg) {
        if (isDebug) {
            if (isSystemOut) {
                System.out.println("tag = [" + tag + "], msg = [" + msg + "]");
            } else {
                Log.i(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            if (isSystemOut) {
                System.out.println("tag = [" + tag + "], msg = [" + msg + "]");
            } else {
                Log.e(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isDebug) {
            if (isSystemOut) {
                System.out.println("tag = [" + tag + "], msg = [" + msg + "]");
            } else {
                Log.e(tag, msg + "||" + tr.getMessage(), tr);
            }
        }
    }
}
