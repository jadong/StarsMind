package com.dong.starsmind.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.dong.starsmind.R;

/**
 * Created by zengwendong on 16/11/2.
 */
public class StartActivity extends AppCompatActivity {

    private LinearLayout ll_root_view;
    private Handler handler = new Handler();
    private Runnable startActivityRun = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        handler.postDelayed(startActivityRun, 500);
        ll_root_view = (LinearLayout) findViewById(R.id.ll_root_view);
        ll_root_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(startActivityRun);
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
