package com.dong.starsmind.todo.view;

import com.dong.starsmind.todo.entity.TODO;

import java.util.List;

/**
 * Created by zengwendong on 16/10/30.
 */
public interface ToDoListView {

    void refreshData(List<TODO> todoList);

}
