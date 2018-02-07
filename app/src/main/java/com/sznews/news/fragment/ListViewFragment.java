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

    public static final String GET_NEWS_URL = "http://v2.sznews.com/NewsDemo/getNewsJSON.php";
    public static final String GET_NEWS_URL1 = "http://v2.sznews.com/NewsDemo/getNewsJSON1.php";
    public static final String GET_NEWS_URL2 = "http://v2.sznews.com/NewsDemo/getNewsJSON2.php";
    public static final String GET_NEWS_URL3 = "http://v2.sznews.com/NewsDemo/getNewsJSON3.php";
    public static final String GET_NEWS_URL4 = "http://v2.sznews.com/NewsDemo/getNewsJSON4.php";
    public static final String GET_NEWS_URL5 = "http://v2.sznews.com/NewsDemo/getNewsJSON5.php";
    public static final String GET_NEWS_URL6 = "http://v2.sznews.com/NewsDemo/getNewsJSON6.php";
    public static final String GET_NEWS_URL7 = "http://v2.sznews.com/NewsDemo/getNewsJSON7.php";

    private Handler getNewsHander = new Handler(){
        public void handleMessage(android.os.Message msg){
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("title");
                    String time = object.getString("time");
                    String content_url = object.getString("content_url");
                    String pic_url = object.getString("pic_url");
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
        view = inflater.inflate(R.layout.newslist,container,false);

        initViews();

        return view;
    }

    public void initViews() {

        lvNews = (ListView) view.findViewById(R.id.lvNews);
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
            }
        }
    }
}
