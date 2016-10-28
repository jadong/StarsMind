package com.dong.starsmind.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zengwendong on 16/10/28.
 */
public class TimeUtils {

    private final static String pattern_1 = "yyyy-MM-dd";
    private final static String pattern_2 = "yyyy-MM-dd hh:mm:ss";
    private final static String pattern_3 = "yyyy-MM-dd hh:mm:ss.SSS";

    /**
     * 格式化时间戳为字符串
     *
     * @param timestamp 时间戳(秒)
     * @return
     */
    public static String formatTimeToString(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_1, Locale.CHINA);
        Date date = new Date();
        date.setTime(timestamp * 1000);
        return sdf.format(date);
    }

}
