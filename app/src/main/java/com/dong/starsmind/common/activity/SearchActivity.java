package com.dong.starsmind.common.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.utils.KeyboardHelper;

/**
 * Created by zengwendong on 16/12/2.
 */
public class SearchActivity extends BaseActivity{

    private SearchView searchView;

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected String getToolBarTitle() {
        return "搜索";
    }

    public static void startActivityForResult(Context context,int requestCode) {
        Activity activity = (Activity) context;
        activity.startActivityForResult(new Intent(context, SearchActivity.class),requestCode);
    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //3.设置为默认展开状态，图标在外面
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                KeyboardHelper.hideSoftKeyboard(SearchActivity.this, searchView);
                execSearch(query);
                return true;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    execSearch("");
                }
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                execSearch("");
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String) searchView.getQuery();
                execSearch(text);
            }
        });

        return true;
    }

    public void execSearch(String keyword){
        Intent data = new Intent();
        data.putExtra("keyword",keyword);
        setResult(RESULT_OK,data);
        finish();
    }
}
