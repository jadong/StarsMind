package com.dong.starsmind.utils;

import android.widget.Toast;

import com.dong.starsmind.app.StarsApplication;

import java.util.UUID;

/**
 * Created by zengwendong on 16/10/27.
 */
public class AppUtils {

    public static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public static void toastShort(String message) {
        Toast.makeText(StarsApplication.appContext, message, Toast.LENGTH_SHORT).show();
    }

}
