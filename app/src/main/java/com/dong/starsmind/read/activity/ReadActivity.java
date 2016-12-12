package com.dong.starsmind.read.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dong.starsmind.utils.AppUtils;
import com.dong.starsmind.widgets.BookPageFactory;
import com.dong.starsmind.widgets.BookPageWidget;

import java.io.IOException;

/**
 * Created by zengwendong on 16/11/4.
 */
public class ReadActivity extends AppCompatActivity {

    private BookPageWidget mPageWidget;
    Bitmap mCurPageBitmap, mNextPageBitmap;
    Canvas mCurPageCanvas, mNextPageCanvas;
    BookPageFactory bookPageFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int screen_width = AppUtils.getScreenWidth();
        int screen_height = AppUtils.getScreenHeight();
        mPageWidget = new BookPageWidget(this);
        mPageWidget.setScreen(screen_width, screen_height);

        setContentView(mPageWidget);

        mCurPageBitmap = Bitmap.createBitmap(screen_width, screen_height, Bitmap.Config.ARGB_8888);
        mNextPageBitmap = Bitmap.createBitmap(screen_width, screen_height, Bitmap.Config.ARGB_8888);
        mCurPageCanvas = new Canvas(mCurPageBitmap);
        mNextPageCanvas = new Canvas(mNextPageBitmap);
        bookPageFactory = new BookPageFactory(screen_width, screen_height);
        //bookPageFactory.setBgBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.book_bg));

        String path = getIntent().getStringExtra("path");
        try {
            bookPageFactory.openBook(path);
            //绘制当前页
            bookPageFactory.onDraw(mCurPageCanvas);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        mPageWidget.setBitmaps(mCurPageBitmap, mCurPageBitmap);
        mPageWidget.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                if (v == mPageWidget) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        mPageWidget.abortAnimation();
                        mPageWidget.calcCornerXY(e.getX(), e.getY());

                        //绘制当前页
                        bookPageFactory.onDraw(mCurPageCanvas);
                        if (mPageWidget.dragToRight()) {
                            try {
                                bookPageFactory.prePage();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            if (bookPageFactory.isFirstPage()) {
                                AppUtils.toastShort("已经是第一页了~");
                                return false;
                            }
                            //绘制下一页
                            bookPageFactory.onDraw(mNextPageCanvas);
                        } else {
                            try {
                                bookPageFactory.nextPage();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            if (bookPageFactory.isLastPage()) {
                                AppUtils.toastShort("已经是最后一页了~");
                                return false;
                            }
                            //绘制下一页
                            bookPageFactory.onDraw(mNextPageCanvas);
                        }
                        mPageWidget.setBitmaps(mCurPageBitmap, mNextPageBitmap);
                    }
                    //postInvalidate
                    mPageWidget.doTouchEvent(e);
                    return true;
                }
                return false;
            }

        });
    }

    private Handler draw_handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };

    public static void startActivity(Context context, String path) {
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra("path", path);
        context.startActivity(intent);
    }
}
