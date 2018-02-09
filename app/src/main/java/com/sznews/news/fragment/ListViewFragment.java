package com.sznews.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sznews.news.R;
import com.sznews.news.adapter.NewsAdapter;
import com.sznews.news.model.News;
import com.sznews.news.utils.HttpUtils;

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
    private List<News> newsList = new ArrayList<News>();

    public static final String GET_NEWS_URL = "https://wx.sznews.com/sznewswx/list_category_176_page_1.json";
    public static final String GET_NEWS_URL1 = "https://wx.sznews.com/sznewswx/list_category_153_page_1.json";
    public static final String GET_NEWS_URL2 = "https://wx.sznews.com/sznewswx/list_category_178_page_1.json";
    public static final String GET_NEWS_URL3 = "https://wx.sznews.com/sznewswx/list_category_174_page_1.json";
    public static final String GET_NEWS_URL4 = "https://wx.sznews.com/sznewswx/list_category_179_page_1.json";
    public static final String GET_NEWS_URL5 = "https://wx.sznews.com/sznewswx/list_category_192_page_1.json";
    public static final String GET_NEWS_URL6 = "https://wx.sznews.com/sznewswx/list_category_191_page_1.json";
    public static final String GET_NEWS_URL7 = "https://wx.sznews.com/sznewswx/list_category_201_page_1.json";
    public static final String GET_NEWS_URL8 = "https://wx.sznews.com/sznewswx/list_category_182_page_1.json";
    public static final String GET_NEWS_URL9 = "https://wx.sznews.com/sznewswx/list_category_181_page_1.json";
    public static final String GET_NEWS_URL10 = "https://wx.sznews.com/sznewswx/list_category_180_page_1.json";
    public static final String GET_NEWS_URL11 = "https://wx.sznews.com/sznewswx/list_category_185_page_1.json";
    public static final String GET_NEWS_URL12 = "https://wx.sznews.com/sznewswx/list_category_186_page_1.json";
    public static final String GET_NEWS_URL13 = "https://wx.sznews.com/sznewswx/list_category_187_page_1.json";
    public static final String GET_NEWS_URL14 = "https://wx.sznews.com/sznewswx/list_category_188_page_1.json";
    public static final String GET_NEWS_URL15 = "https://wx.sznews.com/sznewswx/list_category_184_page_1.json";
    public static final String GET_NEWS_URL16 = "https://wx.sznews.com/sznewswx/list_category_190_page_1.json";
    public static final String GET_NEWS_URL17 = "https://wx.sznews.com/sznewswx/list_category_183_page_1.json";

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

        lvNews = (ListView) view.findViewById(R.id.news_lvNews);
        newsAdapter = new NewsAdapter1(getActivity(), newsList);
        lvNews.setAdapter(newsAdapter);
//        HttpUtils.getNewsJSON(GET_NEWS_URL,getNewsHander);
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
