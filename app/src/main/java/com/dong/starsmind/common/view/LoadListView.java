package com.dong.starsmind.common.view;

import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.todo.entity.TODO;

/**
 * Created by zengwendong on 16/10/30.
 */
public interface LoadListView<T> {

    void refreshData(DBPage<T> dbPage);

}
