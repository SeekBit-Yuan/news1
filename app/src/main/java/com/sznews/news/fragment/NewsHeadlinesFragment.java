package com.sznews.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sznews.news.R;
import com.sznews.news.activity.NewsActivity;
import com.sznews.news.adapter.NewsAdapter;
import com.sznews.news.model.News;
import com.sznews.news.utils.HttpUtils;
import com.sznews.news.utils.LoadListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiy on 2018-1-23.
 */

public class NewsHeadlinesFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View view;

    private ListView lvNews;
    private LoadListView mListView;
    private Handler mHandler;
    private NewsAdapter newsAdapter;
    private List<News> newsList = new ArrayList<News>();
    private SwipeRefreshLayout swipeRefreshLayout;

    public static int GET_NEWS_PAGE = 1;
    public static String GET_NEWS_URL_PREFIX = "https://wx.sznews.com/sznewswx/list_category_176_page_";
    public String GET_NEWS_URL = "https://wx.sznews.com/sznewswx/list_category_176_page_" + GET_NEWS_PAGE +".json";

    private Handler getNewsHander = new Handler(){
        public void handleMessage(android.os.Message msg){
            String jsonData = (String) msg.obj;
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("Title");
                    String time = object.getString("Publishedtime");
                    String pic_url = object.getString("imgurl");
                    String content_url = object.getString("articleId");
                    newsList.add(new News(title,time,pic_url,content_url));
                }
                newsAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.headlines_fragment,container,false);

        initViews();

        return view;
    }

    public void initViews() {

//        lvNews = (ListView) view.findViewById(R.id.lvNews);
        newsAdapter = new NewsAdapter1(getActivity(), newsList);
//        lvNews.setAdapter(newsAdapter);
        mListView = view.findViewById(R.id.listView);
        mListView.setAdapter(newsAdapter);
        HttpUtils.getNewsJSON(GET_NEWS_URL,getNewsHander);
        //ListView上拉加载监听
        mListView.setOnLoadlistener(new LoadListView.OnLoadListener() {
            @Override
            public void onLoading() {
                //模拟网络加载数据延迟
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            mHandler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                }).start();
            }
        });
        //ListView的Item点击监听
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0:
//                        Intent intent0 = new Intent(getActivity(),NewsActivity.class);
//                        startActivity(intent0);
//                        break;
//                }
//            }
//        });

        /**
         * 模拟网络数据加载
         */
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int tag = msg.what;
                switch (tag) {
                    //刷新
                    case 0:
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    //加载更多
                    case 1:
                        GET_NEWS_PAGE +=1;
                        GET_NEWS_URL = "https://wx.sznews.com/sznewswx/list_category_176_page_" + GET_NEWS_PAGE +".json";
                        HttpUtils.getNewsJSON(GET_NEWS_URL,getNewsHander);
                        mListView.setLoadComplete();
                        break;
                }
            }
        };

        swipeRefreshLayout = view.findViewById(R.id.headlines_swipeRefreshLayout);

        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.theme, R.color.colorPrimary, R.color.colorPrimaryDark);

        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                //刷新
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //结束后停止刷新
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 3000);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            mHandler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    class NewsAdapter1 extends NewsAdapter {
        public NewsAdapter1(Context context, List<News> lt) {
            super(context, lt);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
    }
}
