package com.dong.starsmind.todo.activity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.utils.TimeUtils;
import com.dong.starsmind.widgets.TimePickerPopupWindow;

import java.util.Calendar;

/**
 * Created by zengwendong on 16/10/31.
 */
public class AddToDoActivity extends BaseActivity {

    private EditText et_todo_content;
    private TextView tv_reminder_time;
    private Button btn_confirm;
    private LinearLayout ll_root_view;

    private TimePickerPopupWindow timePickerPopupWindow;

    @Override
    protected int getContentView() {
        return R.layout.activity_add_todo;
    }

    @Override
    protected String getToolBarTitle() {
        return getString(R.string.add_todo_title);
    }

    @Override
    protected void initView() {
        ll_root_view = (LinearLayout) findViewById(R.id.ll_root_view);
        et_todo_content = (EditText) findViewById(R.id.et_todo_content);
        tv_reminder_time = (TextView) findViewById(R.id.tv_reminder_time);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        String dateStr = TimeUtils.formatTimeToString(System.currentTimeMillis() / 1000);
        tv_reminder_time.setText(dateStr);
        timePickerPopupWindow = new TimePickerPopupWindow(this);

        timePickerPopupWindow.setTimePickerPopupListener(new TimePickerPopupWindow.TimePickerPopupListener() {
            @Override
            public void onConfirm(String selectDate) {
                tv_reminder_time.setText(selectDate);
            }
        });

        tv_reminder_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerPopupWindow.showAtLocation(ll_root_view, Gravity.BOTTOM, 0, 0);
                String dateStr = tv_reminder_time.getText().toString();
                Calendar calendar = TimeUtils.parseToDate(dateStr);
                if (calendar != null) {
                    timePickerPopupWindow.setDefaultSelect(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
                }
            }
        });
    }
}
