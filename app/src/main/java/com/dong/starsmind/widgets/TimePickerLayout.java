package com.dong.starsmind.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.dong.starsmind.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 日期选择控件
 * Created by zengwendong on 16/10/31.
 */
public class TimePickerLayout extends LinearLayout {
    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;
    private final int maxYear = 2100;
    private final int minYear = 2000;
    private int defaultYearIndex, defaultMonthIndex, defaultDayIndex;//当前默认选择年月日索引

    public TimePickerLayout(Context context) {
        this(context, null);
    }

    public TimePickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.layout_time_picker, this);
        mWheelYear = (WheelView) findViewById(R.id.year);
        mWheelMonth = (WheelView) findViewById(R.id.month);
        mWheelDay = (WheelView) findViewById(R.id.day);

        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH) + 1;
        int currDay = calendar.get(Calendar.DAY_OF_MONTH);

        //设置年份数据
        mWheelYear.setData(getYearData(currYear));
        //设置默认选择年份
        mWheelYear.setDefault(defaultYearIndex);

        //设置月份数据
        mWheelMonth.setData(getMonthData(currMonth));
        //设置默认选择月份
        mWheelMonth.setDefault(defaultMonthIndex);

        //设置一个月中的天数
        mWheelDay.setData(getDayData(currYear, currMonth, currDay));
        //设置默认选择天
        mWheelDay.setDefault(defaultDayIndex);

        setListener();
    }

    private void setListener() {
        mWheelYear.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int index, String text) {
                defaultYearIndex = index;
            }

            @Override
            public void selecting(int index, String text) {

            }
        });
        //月份选择监听
        mWheelMonth.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int index, String monthText) {
                if (TextUtils.isEmpty(monthText) || getYear() == -1) {
                    return;
                }
                int month = Integer.parseInt(monthText);
                int days = getDaysByYearMonth(getYear(), month);
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < days; i++) {
                    list.add(String.valueOf(i + 1));
                }
                mWheelDay.refreshData(list);
                mWheelDay.setDefault(defaultDayIndex);
            }

            @Override
            public void selecting(int index, String text) {

            }
        });

        mWheelDay.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int index, String text) {
                defaultDayIndex = index;
            }

            @Override
            public void selecting(int index, String text) {

            }
        });
    }

    public void refreshDefaultSelect(int year, int month, int day) {
        //设置默认选择年份
        getYearData(year);
        mWheelYear.setDefault(defaultYearIndex);
        //设置默认选择月份
        getMonthData(month);
        mWheelMonth.setDefault(defaultMonthIndex);
        //设置默认选择天
        getDayData(year, month, day);
        mWheelDay.setDefault(defaultDayIndex);
    }

    private ArrayList<String> getYearData(int currYear) {
        ArrayList<String> list = new ArrayList<>();
        int len = maxYear - minYear;
        for (int i = 0; i < len; i++) {
            int year = minYear + i;
            if (currYear == year) {
                defaultYearIndex = i;
            }
            list.add(String.valueOf(year));
        }

        return list;
    }

    private ArrayList<String> getMonthData(int currMonth) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int month = i + 1;
            if (currMonth == month) {
                defaultMonthIndex = i;
            }
            list.add(String.valueOf(month));
        }

        return list;
    }

    private ArrayList<String> getDayData(int currYear, int currMonth, int currDay) {
        int days = getDaysByYearMonth(currYear, currMonth);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            int day = i + 1;
            if (currDay == day) {
                defaultDayIndex = i;
            }
            list.add(String.valueOf(day));
        }

        return list;
    }

    /**
     * 根据年、月获取对应月份的天数
     */
    private int getDaysByYearMonth(int year, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);//回滚一天,不会影响到月份
        return calendar.get(Calendar.DATE);
    }

    public int getYear() {
        if (mWheelDay == null) {
            return -1;
        }
        int year = -1;
        if (!TextUtils.isEmpty(mWheelYear.getSelectedText())) {
            year = Integer.parseInt(mWheelYear.getSelectedText());
        }
        return year;
    }

    public int getMonth() {
        if (mWheelMonth == null) {
            return -1;
        }
        int month = -1;
        if (!TextUtils.isEmpty(mWheelMonth.getSelectedText())) {
            month = Integer.parseInt(mWheelMonth.getSelectedText());
        }
        return month;
    }

    public int getDay() {
        if (mWheelDay == null) {
            return -1;
        }
        int day = -1;
        if (!TextUtils.isEmpty(mWheelDay.getSelectedText())) {
            day = Integer.parseInt(mWheelDay.getSelectedText());
        }
        return day;
    }

    public String getSelectDate() {
        String monthStr = getMonth() < 10 ? ("0" + getMonth()) : getMonth() + "";
        String dayStr = getDay() < 10 ? ("0" + getDay()) : getDay() + "";
        return getYear() + "-" + monthStr + "-" + dayStr;
    }
}
