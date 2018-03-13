package com.sznews.news.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sznews.news.R;
import com.sznews.news.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by qiy on 2018-1-3.
 */

public class NewsFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.news_headlines)
    RadioButton newsHeadlines;
    @BindView(R.id.news_find)
    RadioButton newsFind;
    @BindView(R.id.news_district)
    RadioButton newsDistrict;
    @BindView(R.id.news_tab_bar)
    RadioGroup newsTabBar;
//    @BindView(R.id.viewPager)
//    ViewPager viewPager;
    Unbinder unbinder;

    private NewsHeadlinesFragment newsHeadlinesFragment;
    private NewsFindFragment newsFindFragment;
    private  NewsDistrictFragment newsDistrictFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news, null);
        unbinder = ButterKnife.bind(this, view);

        fragmentManager = getChildFragmentManager();
        newsTabBar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        newsHeadlines = (RadioButton) view.findViewById(R.id.news_headlines);
        newsHeadlines.setChecked(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        fragmentManager = getChildFragmentManager();
//        newsTabBar.setOnCheckedChangeListener();
//        //获取第一个单选按钮，并设置其为选中状态
//        newsHeadlines = (RadioButton) getActivity().findViewById(R.id.radio_news);
//        newsHeadlines.setChecked(true);

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideFragment(fTransaction);
        switch (checkedId) {
            case R.id.news_headlines:
                if (newsHeadlinesFragment == null) {
                    newsHeadlinesFragment = new NewsHeadlinesFragment();
                    fTransaction.add(R.id.news_Framegment, newsHeadlinesFragment);
                } else {
                    fTransaction.show(newsHeadlinesFragment);
                }
                break;
            case R.id.news_find:
                if (newsFindFragment == null) {
                    newsFindFragment  = new NewsFindFragment();
                    fTransaction.add(R.id.news_Framegment, newsFindFragment );
                } else {
                    fTransaction.show(newsFindFragment);
                }
                break;
            case R.id.news_district:
                if (newsDistrictFragment == null) {
                    newsDistrictFragment = new NewsDistrictFragment();
                    fTransaction.add(R.id.news_Framegment, newsDistrictFragment);
                } else {
                    fTransaction.show(newsDistrictFragment);
                }
                break;
        }
        fTransaction.commit();
    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (newsHeadlinesFragment != null) {
            transaction.hide(newsHeadlinesFragment);
        }
        if (newsFindFragment != null) {
            transaction.hide(newsFindFragment);
        }
        if (newsDistrictFragment != null) {
            transaction.hide(newsDistrictFragment);
        }
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
//        viewPager.setAdapter(myFragmentPagerAdapter);
//        viewPager.setOffscreenPageLimit(4);
//        viewPager.setCurrentItem(0);
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
//                if (state == 2) {
//                    switch (viewPager.getCurrentItem()) {
//                        case PAGE_ONE:
//                            newsHeadlines.setChecked(true);
//                            break;
//                        case PAGE_TWO:
//                            newsFind.setChecked(true);
//                            break;
//                        case PAGE_THREE:
//                            newsDistrict.setChecked(true);
//                            break;
//                        case PAGE_FOUR:
//                            newsGovernment.setChecked(true);
//                            break;
//                    }
//                }
//            }
//        });
//
//        newsTabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.news_headlines:
//                        viewPager.setCurrentItem(PAGE_ONE);
//                        break;
//                    case R.id.news_find:
//                        viewPager.setCurrentItem(PAGE_TWO);
//                        break;
//                    case R.id.news_district:
//                        viewPager.setCurrentItem(PAGE_THREE);
//                        break;
//                    case R.id.news_government:
//                        viewPager.setCurrentItem(PAGE_FOUR);
//                        break;
//                }
//            }
//        });
//
//        newsHeadlines.setChecked(true);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
}
