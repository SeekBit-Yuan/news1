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
 * Created by qiy on 2018-1-23.
 */

public class NewsHeadlinesFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View view;

    private ListView lvNews;
    private NewsAdapter newsAdapter;
    private List<News> newsList = new ArrayList<News>();

    public static final String GET_NEWS_URL = "https://wx.sznews.com/sznewswx/list_category_176_page_1.json";

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

        lvNews = (ListView) view.findViewById(R.id.lvNews);
        newsAdapter = new NewsAdapter1(getActivity(), newsList);
        lvNews.setAdapter(newsAdapter);
        HttpUtils.getNewsJSON(GET_NEWS_URL,getNewsHander);
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

//        //获取Activity里传过来的捆绑数据
//        Bundle bundle = getArguments();
//
//        if (bundle != null) {
//            int arg = bundle.getInt("arg");
//            // tv.setText("我是Fagment"+arg);
//            switch (arg) {
//                case 0:
//                    HttpUtils.getNewsJSON(GET_NEWS_URL,getNewsHander);
//                    break;
//            }
//        }
    }
}
