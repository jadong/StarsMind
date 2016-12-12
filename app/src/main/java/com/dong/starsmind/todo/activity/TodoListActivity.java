package com.dong.starsmind.todo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.common.viewholder.FooterViewHolder;
import com.dong.starsmind.constant.RequestCode;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.helper.ItemTouchHelperCallback;
import com.dong.starsmind.todo.adapter.ToDoAdapter;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.presenter.ToDoListPresenter;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

public class TodoListActivity extends BaseActivity implements RefreshListView<DBPage<TODO>> {

    private FloatingActionButton fab_add;
    private LoadMoreRecyclerView rv_todo_list;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ToDoAdapter toDoAdapter;

    private ToDoListPresenter toDoListPresenter;
    private int pageNo = 1;

    /*static {
        //支持Vector资源
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }*/

    @Override
    protected int getContentView() {
        return R.layout.activity_todo_list;
    }

    @Override
    protected String getToolBarTitle() {
        return getString(R.string.event_title);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TodoListActivity.class));
    }

    @Override
    protected void initView() {

        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToDoActivity.startActivity(TodoListActivity.this, null);
            }
        });
        fab_add.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.orange)));


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        //固定recyclerview大小
        rv_todo_list = (LoadMoreRecyclerView) findViewById(R.id.rv_todo_list);
        rv_todo_list.setLayoutManager(new LinearLayoutManager(this));
        rv_todo_list.setHasFixedSize(true);

        setListener();
        setAdapter();

        toDoListPresenter = new ToDoListPresenter(this);
        loadData();
    }

    private void setAdapter() {
        toDoAdapter = new ToDoAdapter(this);
        rv_todo_list.setAdapter(toDoAdapter);

        //关联ItemTouchHelper和RecyclerView
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(toDoAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv_todo_list);
    }

    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                loadData();
            }
        });
        rv_todo_list.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageNo++;
                loadData();
            }
        });


    }

    private void loadData() {
        toDoListPresenter.loadToDoList(pageNo, -1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.ADD_OR_UPDATE && resultCode == RESULT_OK) {
            pageNo = 1;
            loadData();
        }
    }

    @Override
    public void refreshData(DBPage<TODO> dbPage) {
        swipeRefreshLayout.setRefreshing(false);
        if (dbPage.hasNextPage()) {
            toDoAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOADING);
        } else {
            toDoAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOAD_END);
        }
        rv_todo_list.notifyMoreFinish(dbPage.hasNextPage());
        if (dbPage.getPageNo() == 1) {
            if (dbPage.getDataList() == null) {
                toDoAdapter.setFooterViewStatus(FooterViewHolder.STATUS_LOAD_NO_DATA);
                toDoAdapter.notifyFooterView();
            } else {
                toDoAdapter.setData(dbPage.getDataList());
            }
        } else {
            toDoAdapter.addData(dbPage.getDataList());
        }
    }
}
