package com.dong.starsmind.tuling.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;
import com.dong.starsmind.common.adapter.MessageAdapter;
import com.dong.starsmind.tuling.entity.MessageEntity;
import com.dong.starsmind.tuling.entity.MessageJson;
import com.dong.starsmind.tuling.presenter.TuLingPresenter;
import com.dong.starsmind.common.view.RefreshListView;
import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.db.DBPage;
import com.dong.starsmind.utils.KeyboardHelper;
import com.dong.starsmind.widgets.CustomDialog;
import com.dong.starsmind.widgets.LoadMoreRecyclerView;

/**
 * 图灵
 * Created by zengwendong on 16/11/24.
 */
public class TuLingActivity extends BaseActivity implements RefreshListView<MessageEntity> {

    private LinearLayout ll_root_view;
    private EditText et_info;
    private Button btn_send;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreRecyclerView rv_msg_list;
    private LinearLayoutManager linearLayoutManager;

    private TuLingPresenter tuLingPresenter;
    private MessageAdapter messageAdapter;
    private DBPage<MessageEntity> dataPage;

    @Override
    protected int getContentView() {
        return R.layout.activity_tuling;
    }

    @Override
    protected String getToolBarTitle() {
        return "图灵";
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TuLingActivity.class));
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    @Override
    protected void initView() {
        tuLingPresenter = new TuLingPresenter(this);
        ll_root_view = (LinearLayout) findViewById(R.id.ll_root_view);
        et_info = (EditText) findViewById(R.id.et_info);
        btn_send = (Button) findViewById(R.id.btn_send);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        rv_msg_list = (LoadMoreRecyclerView) findViewById(R.id.rv_msg_list);
        linearLayoutManager = new LinearLayoutManager(this);
        rv_msg_list.setLayoutManager(linearLayoutManager);
        messageAdapter = new MessageAdapter(this);
        rv_msg_list.setAdapter(messageAdapter);
        setListener();

        dataPage = tuLingPresenter.getHistoryMessage(1);
        messageAdapter.addData(dataPage.getDataList());
        scrollToFooter();
    }

    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (dataPage != null && dataPage.hasNextPage()) {
                            int oldCount = messageAdapter.getItemCount();
                            dataPage = tuLingPresenter.getHistoryMessage(dataPage.getNextPageNo());
                            messageAdapter.addDataToFirst(dataPage.getDataList());
                            linearLayoutManager.scrollToPosition(messageAdapter.getItemCount() - oldCount);
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        });
        rv_msg_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                KeyboardHelper.hideSoftKeyboard(TuLingActivity.this, et_info);
                return false;
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = et_info.getText().toString();
                if (TextUtils.isEmpty(info)) {
                    return;
                }
                tuLingPresenter.sendMessage(info);
                et_info.setText("");
                MessageEntity entity = new MessageEntity(info);
                messageAdapter.addOneData(entity);
                scrollToFooter();

                //保存数据库
                DBOperation.saveOrUpdate(new MessageJson(0, entity.toJson()));
            }
        });
        KeyboardHelper.addKeyboardShowListener(ll_root_view, new KeyboardHelper.OnKeyboardShowListener() {
            @Override
            public void onKeyboardShow(boolean show) {
                if (show) {
                    scrollToFooter();
                }
            }
        });
    }

    //滚动到底部
    private void scrollToFooter() {
        linearLayoutManager.scrollToPosition(messageAdapter.getItemCount() - 1);
    }

    @Override
    public void refreshData(MessageEntity messageEntity) {
        messageAdapter.addOneData(messageEntity);
        scrollToFooter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.tuling_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.btn_clear:

                CustomDialog.Builder builder = new CustomDialog.Builder(this);
                builder.setMessage("确定清除所有记录吗?");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //刪除所有消息记录
                        DBOperation.deleteAll(MessageJson.class);
                        messageAdapter.clearData();
                        dataPage = null;
                    }
                });

                builder.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.create().show();

                break;
            default:
                break;
        }
        return true;
    }
}
