package com.sznews.news.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.sznews.news.R;
import com.sznews.news.model.News;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiy on 2018-1-24.
 */

public class NewsActivity extends Activity{
    private ImageView news_back;
    private WebView webView;
    private ImageView news_share;
    private List<News> newsList = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        news_back = (ImageView) findViewById(R.id.news_back);
        news_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        news_share = (ImageView) findViewById(R.id.news_share);
        news_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView = (WebView) findViewById(R.id.newsavtivity_webview);

        String pic_url = "http://app2.sznews.com/shenzhen/index.php?s=/Public/newsview/nid/" + getIntent().getStringExtra("content_url") + "&show=1";

        webView.loadUrl(pic_url);
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
