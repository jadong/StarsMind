package com.dong.starsmind.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
     * @return yyyy-MM-dd
     */
    public static String formatTimeToString(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern_1, Locale.CHINA);
        Date date = new Date();
        date.setTime(timestamp * 1000);
        return sdf.format(date);
    }

    /**
     * 解析字符串日期
     *
     * @param dateStr yyyy-MM-dd
     * @return Calendar
     */
    public static Calendar parseToCalendar(String dateStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern_1, Locale.CHINA);
            Calendar calendar = Calendar.getInstance();
            Date parseDate = simpleDateFormat.parse(dateStr);
            calendar.setTime(parseDate);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回时间戳
     *
     * @param dateStr yyyy-MM-dd
     * @return 时间戳,秒
     */
    public static long parseToTimestamp(String dateStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern_1, Locale.CHINA);
            Date date = simpleDateFormat.parse(dateStr);
            return date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        String yearStr = sdf.format(date);
        return Integer.parseInt(yearStr);
    }

    public static int getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.CHINA);
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        String monthStr = sdf.format(date);
        return Integer.parseInt(monthStr);
    }

    public static int getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.CHINA);
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        String dayStr = sdf.format(date);
        return Integer.parseInt(dayStr);
    }

}
