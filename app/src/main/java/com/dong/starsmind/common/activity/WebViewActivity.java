package com.dong.starsmind.common.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dong.starsmind.R;
import com.dong.starsmind.base.BaseActivity;

/**
 * Created by zengwendong on 16/11/22.
 */
public class WebViewActivity extends BaseActivity {

    private WebView webView;
    private String title = "";

    @Override
    protected int getContentView() {
        return R.layout.actvity_webview;
    }

    @Override
    protected String getToolBarTitle() {
        return title;
    }

    public static void startActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setBuiltInZoomControls(false);// 设置不显示缩放按钮
        webView.getSettings().setSupportZoom(true); // 支持缩放
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new MyWebViewClient());
        title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.contains("baidumap://")) {
                view.loadUrl(url);
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }


    }
}
