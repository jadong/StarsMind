package com.dong.starsmind.todo.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.utils.TimeUtils;

/**
 * Created by zengwendong on 16/10/28.
 */
public class ToDoViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_time;
    private TextView tv_content;
    private Spinner spinner_status;
    private Context context;

    public ToDoViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        spinner_status = (Spinner) itemView.findViewById(R.id.spinner_status);

        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //选中
                String[] todo_status = context.getResources().getStringArray(R.array.todo_status);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    public void setData(TODO todo) {
        if (todo == null) {
            return;
        }
        tv_time.setText(TimeUtils.formatTimeToString(todo.getTimestamp()));
        tv_content.setText(todo.getContent());
    }

}
