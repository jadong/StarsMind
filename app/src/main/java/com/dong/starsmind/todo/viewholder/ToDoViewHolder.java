package com.dong.starsmind.todo.viewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dong.starsmind.R;
import com.dong.starsmind.todo.activity.AddToDoActivity;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.presenter.DeleteToDoPresenter;
import com.dong.starsmind.todo.presenter.UpdateToDoPresenter;
import com.dong.starsmind.todo.view.UpdateTodoView;

/**
 * Created by zengwendong on 16/10/28.
 */
public class ToDoViewHolder extends RecyclerView.ViewHolder implements UpdateTodoView {

    private CardView cardView;
    private TextView tv_time;
    private TextView tv_content;
    private Spinner spinner_status;
    private Context context;
    private TODO todo;
    private UpdateToDoPresenter updateToDoPresenter;

    public ToDoViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        cardView = (CardView) itemView.findViewById(R.id.card_view_todo);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        spinner_status = (Spinner) itemView.findViewById(R.id.spinner_status);
        String[] todo_status = context.getResources().getStringArray(R.array.todo_status);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.layout_spinner_item, todo_status);
        spinner_status.setAdapter(adapter);
        setListener();
        updateToDoPresenter = new UpdateToDoPresenter(this);
    }

    private void setListener() {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToDoActivity.startActivity(context, todo.getId());
            }
        });

        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //选中
                if (pos != todo.getStatus()) {
                    todo.setStatus(pos);
                    updateToDoPresenter.saveOrUpdate();
                }
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
        this.todo = todo;
        tv_time.setText(todo.getReminderTimeStr());
        tv_content.setText(todo.getContent());
        spinner_status.setSelection(todo.getStatus());
    }

    @Override
    public TODO getTodo() {
        return todo;
    }

    @Override
    public void onSuccess(Object o) {

    }
}
