package com.dong.starsmind.todo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.constant.RequestCode;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.presenter.AddToDoPresenter;
import com.dong.starsmind.todo.presenter.UpdateToDoPresenter;
import com.dong.starsmind.todo.view.AddTodoView;
import com.dong.starsmind.todo.view.UpdateTodoView;
import com.dong.starsmind.utils.TimeUtils;
import com.dong.starsmind.widgets.TimePickerPopupWindow;

import java.util.Calendar;

/**
 * 新增或修改todo
 * Created by zengwendong on 16/10/31.
 */
public class AddToDoActivity extends BaseActivity implements UpdateTodoView, AddTodoView, View.OnClickListener {

    private EditText et_todo_content;
    private TextView tv_reminder_time;
    private Button btn_confirm;
    private LinearLayout ll_root_view;

    private TimePickerPopupWindow timePickerPopupWindow;

    private AddToDoPresenter addToDoPresenter;
    private UpdateToDoPresenter updateToDoPresenter;
    private TODO todo;

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
        timePickerPopupWindow = new TimePickerPopupWindow(this);
        setListener();

        addToDoPresenter = new AddToDoPresenter(this);
        updateToDoPresenter = new UpdateToDoPresenter(this);
        initData();
    }

    private void initData() {
        int todoId = getIntent().getIntExtra("id", -1);
        if (todoId > 0) {
            todo = addToDoPresenter.getToDo(todoId);
        }
        String dateStr;
        if (todo != null) {
            dateStr = todo.getReminderTimeStr();
            et_todo_content.setText(todo.getContent());
        } else {
            dateStr = TimeUtils.formatTimeToString(System.currentTimeMillis() / 1000);//当前时间
        }
        tv_reminder_time.setText(dateStr);
    }

    private void setListener() {
        tv_reminder_time.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        timePickerPopupWindow.setTimePickerPopupListener(new TimePickerPopupWindow.TimePickerPopupListener() {
            @Override
            public void onConfirm(String selectDate) {
                tv_reminder_time.setText(selectDate);
            }
        });
    }

    public static void startActivity(Context context, Integer todoId) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, AddToDoActivity.class);
        if (todoId != null) {
            intent.putExtra("id", todoId);
        }
        ((Activity) context).startActivityForResult(intent, RequestCode.ADD_OR_UPDATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_reminder_time:
                shopDatePopup();//显示日期选择控件
                break;
            case R.id.btn_confirm:
                //保存
                saveOrUpdate();
                break;
            default:
                break;
        }
    }

    private void saveOrUpdate() {
        //保存
        if (todo != null) {
            updateToDoPresenter.saveOrUpdate();
        } else {
            addToDoPresenter.saveOrUpdate();
        }
    }

    /**
     * 显示日期选择控件
     */
    private void shopDatePopup() {
        timePickerPopupWindow.showAtLocation(ll_root_view, Gravity.BOTTOM, 0, 0);
        String dateStr = tv_reminder_time.getText().toString();
        Calendar calendar = TimeUtils.parseToCalendar(dateStr);
        if (calendar != null) {
            timePickerPopupWindow.setDefaultSelect(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        }
    }

    @Override
    public String getContent() {
        return et_todo_content.getText().toString();
    }

    @Override
    public String getReminderTime() {
        return tv_reminder_time.getText().toString();
    }

    @Override
    public void onSuccess(Object o) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public TODO getTodo() {
        todo.setContent(getContent());
        todo.setReminderTimeStr(getReminderTime());
        return todo;
    }
}
