package com.dong.starsmind.widgets;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.dong.starsmind.R;

/**
 * Created by zengwendong on 16/10/31.
 */
public class TimePickerPopupWindow extends PopupWindow implements View.OnClickListener {

    private TimePickerLayout timePickerLayout;
    private Button btn_confirm;

    private TimePickerPopupListener timePickerPopupListener;

    public TimePickerPopupWindow(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_timepicker_popup, null);
        timePickerLayout = (TimePickerLayout) view.findViewById(R.id.timePickerLayout);
        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);

        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    public void setTimePickerPopupListener(TimePickerPopupListener timePickerPopupListener) {
        this.timePickerPopupListener = timePickerPopupListener;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }

    public void setDefaultSelect(int year,int month,int day){
        timePickerLayout.refreshDefaultSelect(year,month,day);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_confirm) {
            if (timePickerPopupListener != null) {
                String selectDate = timePickerLayout.getSelectDate();
                timePickerPopupListener.onConfirm(selectDate);
                dismiss();
            }
        }
    }

    public interface TimePickerPopupListener {
        void onConfirm(String selectDate);
    }
}
