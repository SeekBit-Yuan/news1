package com.sznews.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.sznews.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiy on 2018-1-23.
 */

public class BBSFragment extends Fragment {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.layout)
    RelativeLayout layout;
    Unbinder unbinder;
    private final String HOME_PAGE = "https://szbbs.sznews.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.szbbs, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WebView webView = (WebView) getActivity().findViewById(R.id.webView);

        //webView属性
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(HOME_PAGE); //查看模块器能否正常上网
        webView.getSettings().setJavaScriptEnabled(true);//是否支持JS
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setSupportZoom(true);//是否支持缩放
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);//不加载网络上的图片资源
        webView.setInitialScale(70);
        webView.setHorizontalScrollBarEnabled(true);
        webView.getSettings().setUseWideViewPort(true);//webview网页自适应手机屏幕大小,设置webview推荐使用的窗口
        webView.getSettings().setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

        //WebChromeClient是辅助处理JavaScript的对话框、网站图标。网站title，加载进度等
        webView.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view,int progress)
            {
                super.onProgressChanged(view, progress);
                System.out.println("onProgressChanged " + progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(progress);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
