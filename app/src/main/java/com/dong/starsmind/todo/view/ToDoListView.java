package com.dong.starsmind.todo.view;

import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.todo.entity.TODO;

/**
 * Created by zengwendong on 16/10/30.
 */
public interface ToDoListView {

    void refreshData(DBPage<TODO> dbPage);

}
