package com.sznews.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sznews.news.R;
import com.sznews.news.adapter.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiy on 2018-1-23.
 */

public class NewsDistrictFragment extends Fragment{

    private String[] titles = new String[]{"头条","深圳","焦点","娱乐","图片","美食","生活","旅游",
            "福田","罗湖","南山","盐田","宝安","龙岗","光明","坪山","龙华","大鹏"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        for(int i=0;i<titles.length;i++){
            Fragment fragment = new ListViewFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("arg",i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
//            fragmentList.add(CategoryTabStrip.newInstance(titles[i]));
        }

        FragmentPagerAdapter adapter = new MyFragmentAdapter(fragmentList,titles,getChildFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
