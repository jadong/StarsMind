package com.dong.starsmind.todo.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.constant.AppConstant;
import com.dong.starsmind.constant.RequestCode;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.helper.ItemTouchHelperCallback;
import com.dong.starsmind.todo.adapter.ToDoAdapter;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.presenter.ToDoListPresenter;
import com.dong.starsmind.todo.view.ToDoListView;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, ToDoListView {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FloatingActionButton fab_add;
    private LoadMoreRecyclerView rv_todo_list;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ToDoAdapter toDoAdapter;

    private ToDoListPresenter toDoListPresenter;
    private int pageNo = 1;

    @Override
    protected boolean getEnableSwipeBack() {
        return false;//禁用滑动退出
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected String getToolBarTitle() {
        return getString(R.string.home_title);
    }

    @Override
    protected void initView() {
        toDoListPresenter = new ToDoListPresenter(this);
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                AddToDoActivity.startActivity(MainActivity.this, null);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);// 此处屏蔽Android系统提供的默认渐变过渡灰黑
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //toggle.setHomeAsUpIndicator(0);//修改默认图标
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
            //设置MenuItem的字体颜色
            ColorStateList csl= getResources().getColorStateList(R.color.left_menu_item_color);
            navigationView.setItemTextColor(csl);
        }

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        //固定recyclerview大小
        rv_todo_list = (LoadMoreRecyclerView) findViewById(R.id.rv_todo_list);
        rv_todo_list.setLayoutManager(new LinearLayoutManager(this));
        rv_todo_list.setHasFixedSize(true);

        setListener();
        setAdapter();

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

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = drawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;
                float leftScale = 1 - 0.3f * scale;

                drawerView.setScaleX(leftScale);
                drawerView.setScaleY(leftScale);
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                mContent.setPivotX(0);
                mContent.setPivotY(mContent.getMeasuredHeight() / 2);
                mContent.invalidate();
                mContent.setScaleX(rightScale);
                mContent.setScaleY(rightScale);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
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
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void refreshData(DBPage<TODO> dbPage) {
        swipeRefreshLayout.setRefreshing(false);
        if (dbPage.hasNextPage()) {
            toDoAdapter.setFooterViewStatus(AppConstant.STATUS_LOADING);
        } else {
            toDoAdapter.setFooterViewStatus(AppConstant.STATUS_LOAD_END);
        }
        rv_todo_list.notifyMoreFinish(dbPage.hasNextPage());
        if (dbPage.getPageNo() == 1) {
            if (dbPage.getDataList() == null) {
                toDoAdapter.setFooterViewStatus(AppConstant.STATUS_LOAD_NO_DATA);
                toDoAdapter.notifyFooterView();
            } else {
                toDoAdapter.setData(dbPage.getDataList());
            }
        } else {
            toDoAdapter.addData(dbPage.getDataList());
        }
    }
}
