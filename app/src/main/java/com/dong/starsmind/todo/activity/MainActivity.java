package com.dong.starsmind.todo.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.todo.adapter.ToDoAdapter;
import com.dong.starsmind.todo.entity.TODO;
import com.dong.starsmind.todo.presenter.ToDoListPresenter;
import com.dong.starsmind.todo.view.ToDoListView;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

import java.util.List;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, ToDoListView {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FloatingActionButton fab_add;
    private LoadMoreRecyclerView rv_todo_list;
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
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //toggle.setHomeAsUpIndicator(0);//修改默认图标
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        rv_todo_list = (LoadMoreRecyclerView) findViewById(R.id.rv_todo_list);
        rv_todo_list.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageNo++;
                toDoListPresenter.loadToDoList(pageNo);
            }
        });
        toDoAdapter = new ToDoAdapter(this);
        rv_todo_list.setAdapter(toDoAdapter);
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
    public void refreshData(List<TODO> todoList) {
        toDoAdapter.addData(todoList);
    }
}
