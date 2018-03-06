package com.sznews.news.fragment;

import android.content.Context;
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
import com.sznews.news.adapter.NewsAdapter;
import com.sznews.news.model.News;
import com.sznews.news.utils.HttpUtils;
import com.sznews.news.utils.LoadListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiy on 2018-2-7.
 */

public class ListViewFragment extends Fragment implements AdapterView.OnItemClickListener{
    private View view;

    private ListView lvNews;
    private NewsAdapter newsAdapter;
    private LoadListView mListView;
    private Handler mHandler;
    private List<News> newsList = new ArrayList<News>();
    private SwipeRefreshLayout swipeRefreshLayout;

    public static int GET_NEWS_PAGE = 1;
    public static int GET_NEWS_PAGE1 = 1;
    public static int GET_NEWS_PAGE2 = 1;
    public static int GET_NEWS_PAGE3 = 1;
    public static int GET_NEWS_PAGE4 = 1;
    public static int GET_NEWS_PAGE5 = 1;
    public static int GET_NEWS_PAGE6 = 1;
    public static int GET_NEWS_PAGE7 = 1;
    public static int GET_NEWS_PAGE8 = 1;
    public static int GET_NEWS_PAGE9 = 1;
    public static int GET_NEWS_PAGE10 = 1;
    public static int GET_NEWS_PAGE11 = 1;
    public static int GET_NEWS_PAGE12 = 1;
    public static int GET_NEWS_PAGE13 = 1;
    public static int GET_NEWS_PAGE14 = 1;
    public static int GET_NEWS_PAGE15 = 1;
    public static int GET_NEWS_PAGE16 = 1;
    public static int GET_NEWS_PAGE17 = 1;

    public String GET_NEWS_URL = "https://wx.sznews.com/sznewswx/list_category_176_page_1.json";
    public String GET_NEWS_URL1 = "https://wx.sznews.com/sznewswx/list_category_153_page_1.json";
    public String GET_NEWS_URL2 = "https://wx.sznews.com/sznewswx/list_category_178_page_1.json";
    public String GET_NEWS_URL3 = "https://wx.sznews.com/sznewswx/list_category_174_page_1.json";
    public String GET_NEWS_URL4 = "https://wx.sznews.com/sznewswx/list_category_179_page_1.json";
    public String GET_NEWS_URL5 = "https://wx.sznews.com/sznewswx/list_category_192_page_1.json";
    public String GET_NEWS_URL6 = "https://wx.sznews.com/sznewswx/list_category_191_page_1.json";
    public String GET_NEWS_URL7 = "https://wx.sznews.com/sznewswx/list_category_201_page_1.json";
    public String GET_NEWS_URL8 = "https://wx.sznews.com/sznewswx/list_category_182_page_1.json";
    public String GET_NEWS_URL9 = "https://wx.sznews.com/sznewswx/list_category_181_page_1.json";
    public String GET_NEWS_URL10 = "https://wx.sznews.com/sznewswx/list_category_180_page_1.json";
    public String GET_NEWS_URL11 = "https://wx.sznews.com/sznewswx/list_category_185_page_1.json";
    public String GET_NEWS_URL12 = "https://wx.sznews.com/sznewswx/list_category_186_page_1.json";
    public String GET_NEWS_URL13 = "https://wx.sznews.com/sznewswx/list_category_187_page_1.json";
    public String GET_NEWS_URL14 = "https://wx.sznews.com/sznewswx/list_category_188_page_1.json";
    public String GET_NEWS_URL15 = "https://wx.sznews.com/sznewswx/list_category_184_page_1.json";
    public String GET_NEWS_URL16 = "https://wx.sznews.com/sznewswx/list_category_190_page_1.json";
    public String GET_NEWS_URL17 = "https://wx.sznews.com/sznewswx/list_category_183_page_1.json";

    private Handler getNewsHander = new Handler(){
        public void handleMessage(android.os.Message msg){
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
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
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.newslist,container,false);

        initViews();

        return view;
    }

    public void initViews() {

//        lvNews = (ListView) view.findViewById(R.id.news_lvNews);
        newsAdapter = new NewsAdapter1(getActivity(), newsList);
//        lvNews.setAdapter(newsAdapter);
        mListView = view.findViewById(R.id.listView);
        mListView.setAdapter(newsAdapter);
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
                        //获取Activity里传过来的捆绑数据
                        Bundle bundle = getArguments();
                        if (bundle != null) {
                            int arg = bundle.getInt("arg");
                            switch (arg) {
                                case 0:
                                    GET_NEWS_PAGE +=1;
                                    GET_NEWS_URL = "https://wx.sznews.com/sznewswx/list_category_176_page_" + GET_NEWS_PAGE +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL,getNewsHander);
                                    break;
                                case 1:
                                    GET_NEWS_PAGE1 +=1;
                                    GET_NEWS_URL1 = "https://wx.sznews.com/sznewswx/list_category_153_page_" + GET_NEWS_PAGE1 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL1,getNewsHander);
                                    break;
                                case 2:
                                    GET_NEWS_PAGE2 +=1;
                                    GET_NEWS_URL2 = "https://wx.sznews.com/sznewswx/list_category_178_page_" + GET_NEWS_PAGE2 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL2,getNewsHander);
                                    break;
                                case 3:
                                    GET_NEWS_PAGE3 +=1;
                                    GET_NEWS_URL3 = "https://wx.sznews.com/sznewswx/list_category_174_page_" + GET_NEWS_PAGE3 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL3,getNewsHander);
                                    break;
                                case 4:
                                    GET_NEWS_PAGE4 +=1;
                                    GET_NEWS_URL4 = "https://wx.sznews.com/sznewswx/list_category_179_page_" + GET_NEWS_PAGE4 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL4,getNewsHander);
                                    break;
                                case 5:
                                    GET_NEWS_PAGE5 +=1;
                                    GET_NEWS_URL5 = "https://wx.sznews.com/sznewswx/list_category_192_page_" + GET_NEWS_PAGE5 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL5,getNewsHander);
                                    break;
                                case 6:
                                    GET_NEWS_PAGE6 +=1;
                                    GET_NEWS_URL6 = "https://wx.sznews.com/sznewswx/list_category_191_page_" + GET_NEWS_PAGE6 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL6,getNewsHander);
                                    break;
                                case 7:
                                    GET_NEWS_PAGE7 +=1;
                                    GET_NEWS_URL7 = "https://wx.sznews.com/sznewswx/list_category_201_page_" + GET_NEWS_PAGE7 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL7,getNewsHander);
                                    break;
                                case 8:
                                    GET_NEWS_PAGE8 +=1;
                                    GET_NEWS_URL8 = "https://wx.sznews.com/sznewswx/list_category_182_page_" + GET_NEWS_PAGE8 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL8,getNewsHander);
                                    break;
                                case 9:
                                    GET_NEWS_PAGE9 +=1;
                                    GET_NEWS_URL9 = "https://wx.sznews.com/sznewswx/list_category_181_page_" + GET_NEWS_PAGE9 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL9,getNewsHander);
                                    break;
                                case 10:
                                    GET_NEWS_PAGE10 +=1;
                                    GET_NEWS_URL10 = "https://wx.sznews.com/sznewswx/list_category_180_page_" + GET_NEWS_PAGE10 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL10,getNewsHander);
                                    break;
                                case 11:
                                    GET_NEWS_PAGE11 +=1;
                                    GET_NEWS_URL11 = "https://wx.sznews.com/sznewswx/list_category_185_page_" + GET_NEWS_PAGE11 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL11,getNewsHander);
                                    break;
                                case 12:
                                    GET_NEWS_PAGE12 +=1;
                                    GET_NEWS_URL12 = "https://wx.sznews.com/sznewswx/list_category_186_page_" + GET_NEWS_PAGE12 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL12,getNewsHander);
                                    break;
                                case 13:
                                    GET_NEWS_PAGE13 +=1;
                                    GET_NEWS_URL13 = "https://wx.sznews.com/sznewswx/list_category_187_page_" + GET_NEWS_PAGE13 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL13,getNewsHander);
                                    break;
                                case 14:
                                    GET_NEWS_PAGE14 +=1;
                                    GET_NEWS_URL14 = "https://wx.sznews.com/sznewswx/list_category_188_page_" + GET_NEWS_PAGE14 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL14,getNewsHander);
                                    break;
                                case 15:
                                    GET_NEWS_PAGE15 +=1;
                                    GET_NEWS_URL15 = "https://wx.sznews.com/sznewswx/list_category_184_page_" + GET_NEWS_PAGE15 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL15,getNewsHander);
                                    break;
                                case 16:
                                    GET_NEWS_PAGE16 +=1;
                                    GET_NEWS_URL16 = "https://wx.sznews.com/sznewswx/list_category_190_page_" + GET_NEWS_PAGE16 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL16,getNewsHander);
                                    break;
                                case 17:
                                    GET_NEWS_PAGE17 +=1;
                                    GET_NEWS_URL17 = "https://wx.sznews.com/sznewswx/list_category_183_page_" + GET_NEWS_PAGE17 +".json";
                                    HttpUtils.getNewsJSON(GET_NEWS_URL17,getNewsHander);
                                    break;
                            }
                        }
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

        //获取Activity里传过来的捆绑数据
        Bundle bundle = getArguments();

        if (bundle != null) {
            int arg = bundle.getInt("arg");
//            System.out.println("arg" + arg);
            // tv.setText("我是Fagment"+arg);
            switch (arg) {
                case 0:
                    HttpUtils.getNewsJSON(GET_NEWS_URL,getNewsHander);
                    break;
                case 1:
                    HttpUtils.getNewsJSON(GET_NEWS_URL1,getNewsHander);
                    break;
                case 2:
                    HttpUtils.getNewsJSON(GET_NEWS_URL2,getNewsHander);
                    break;
                case 3:
                    HttpUtils.getNewsJSON(GET_NEWS_URL3,getNewsHander);
                    break;
                case 4:
                    HttpUtils.getNewsJSON(GET_NEWS_URL4,getNewsHander);
                    break;
                case 5:
                    HttpUtils.getNewsJSON(GET_NEWS_URL5,getNewsHander);
                    break;
                case 6:
                    HttpUtils.getNewsJSON(GET_NEWS_URL6,getNewsHander);
                    break;
                case 7:
                    HttpUtils.getNewsJSON(GET_NEWS_URL7,getNewsHander);
                    break;
                case 8:
                    HttpUtils.getNewsJSON(GET_NEWS_URL8,getNewsHander);
                    break;
                case 9:
                    HttpUtils.getNewsJSON(GET_NEWS_URL9,getNewsHander);
                    break;
                case 10:
                    HttpUtils.getNewsJSON(GET_NEWS_URL10,getNewsHander);
                    break;
                case 11:
                    HttpUtils.getNewsJSON(GET_NEWS_URL11,getNewsHander);
                    break;
                case 12:
                    HttpUtils.getNewsJSON(GET_NEWS_URL12,getNewsHander);
                    break;
                case 13:
                    HttpUtils.getNewsJSON(GET_NEWS_URL13,getNewsHander);
                    break;
                case 14:
                    HttpUtils.getNewsJSON(GET_NEWS_URL14,getNewsHander);
                    break;
                case 15:
                    HttpUtils.getNewsJSON(GET_NEWS_URL15,getNewsHander);
                    break;
                case 16:
                    HttpUtils.getNewsJSON(GET_NEWS_URL16,getNewsHander);
                    break;
                case 17:
                    HttpUtils.getNewsJSON(GET_NEWS_URL17,getNewsHander);
                    break;
            }
        }
    }
}
