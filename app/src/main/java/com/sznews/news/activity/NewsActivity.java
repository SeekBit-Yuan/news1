package com.sznews.news.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sznews.news.R;
import com.sznews.news.model.News;
import com.sznews.news.utils.MyPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiy on 2018-1-24.
 */

public class NewsActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.news_share)
    ImageView newsShare;
    @BindView(R.id.editText_news)
    EditText editTextNews;
    @BindView(R.id.news_message)
    ImageView newsMessage;
    @BindView(R.id.news_like)
    ImageView newsLike;
    @BindView(R.id.news_collection)
    ImageView newsCollection;
    @BindView(R.id.news_back)
    ImageView newsBack;

    private ImageView news_back;
    private WebView webView;
    private ImageView news_share;
    private List<News> newsList = new ArrayList<News>();
    private MyPopupWindow myPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        ButterKnife.bind(this);

        webView = (WebView) findViewById(R.id.newsavtivity_webview);

        String pic_url = "http://app2.sznews.com/shenzhen/index.php?s=/Public/newsview/nid/" + getIntent().getStringExtra("content_url") + "&show=1";

        webView.loadUrl(pic_url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setSupportZoom(true);//是否支持缩放
        webView.getSettings().setUseWideViewPort(true);//webview网页自适应手机屏幕大小,设置webview推荐使用的窗口

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url); // 在当前的webview中跳转到新的url
                return true;
            }
        });

        initView();

    }

    public void initView(){

        newsBack.setOnClickListener(this);
        newsShare.setOnClickListener(this);
        newsMessage.setOnClickListener(this);
        newsLike.setOnClickListener(this);
        newsCollection.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_back:
                finish();
                break;
            case R.id.news_share:
                myPopupWindow = new MyPopupWindow(NewsActivity.this,itemsOnClick);
                myPopupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.news_message:
                Toast.makeText(NewsActivity.this, getString(R.string.sideslip_collection), Toast.LENGTH_SHORT).show();
                break;
            case R.id.news_like:
                newsLike.setImageDrawable(getResources().getDrawable(R.mipmap.zan_selected));
                break;
            case R.id.news_collection:
                newsCollection.setImageDrawable(getResources().getDrawable(R.mipmap.collection_selected));
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            myPopupWindow.dismiss();
            myPopupWindow.backgroundAlpha(NewsActivity.this, 1f);
            switch (v.getId()) {
                case R.id.wechat:
                    Toast.makeText(NewsActivity.this, "微信分享", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.circle:
                    Toast.makeText(NewsActivity.this, "朋友圈分享", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.qqhaoyou:
                    Toast.makeText(NewsActivity.this, "QQ分享", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.qqzone:
                    Toast.makeText(NewsActivity.this, "QQ空间分享", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.weibo:
                    Toast.makeText(NewsActivity.this, "新浪微博分享", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.link:
                    Toast.makeText(NewsActivity.this, "复制链接分享", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

    };

}
