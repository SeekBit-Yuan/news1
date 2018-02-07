package com.sznews.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.sznews.news.fragment.NewsDistrictFragment;
import com.sznews.news.fragment.NewsFindFragment;
import com.sznews.news.fragment.NewsFragment;
import com.sznews.news.fragment.NewsGovernmentFragment;
import com.sznews.news.fragment.NewsHeadlinesFragment;

import java.util.List;

/**
 * Created by qiy on 2018-1-23.
 */

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private final int PAGER_COUNT = 4;
    private NewsHeadlinesFragment newsHeadlinesFragment;
    private NewsFindFragment newsFindFragment;
    private NewsDistrictFragment newsDistrictFragment;
    private NewsGovernmentFragment newsGovernmentFragment;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        newsHeadlinesFragment = new NewsHeadlinesFragment();
        newsFindFragment = new NewsFindFragment();
        newsDistrictFragment = new NewsDistrictFragment();
        newsGovernmentFragment = new NewsGovernmentFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case NewsFragment.PAGE_ONE:
                fragment = newsHeadlinesFragment;
                break;
            case NewsFragment.PAGE_TWO:
                fragment = newsFindFragment;
                break;
            case NewsFragment.PAGE_THREE:
                fragment = newsDistrictFragment;
                break;
            case NewsFragment.PAGE_FOUR:
                fragment = newsGovernmentFragment;
                break;
        }
        return fragment;
    }

}
