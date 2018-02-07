package com.sznews.news.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sznews.news.R;

/**
 * Created by qiy on 2018-1-29.
 */

public class SzbbsActivity extends Activity{

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digitalnewspaper_activity);

        webView = (WebView) findViewById(R.id.dnavtivity_webview);
        String url = "https://szbbs.sznews.com/";
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setSupportZoom(true);//是否支持缩放
        webView.getSettings().setUseWideViewPort(true);//webview网页自适应手机屏幕大小,设置webview推荐使用的窗口

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url); // 在当前的webview中跳转到新的url
                return true;
            }
        });

    }

}
