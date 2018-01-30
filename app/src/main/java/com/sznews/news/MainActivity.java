package com.sznews.news;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sznews.news.fragment.BBSFragment;
import com.sznews.news.fragment.NewsFragment;
import com.sznews.news.fragment.SemartCityFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.radio_news)
    RadioButton radioNews;
    @BindView(R.id.radio_smartcity)
    RadioButton radioSmartcity;
    @BindView(R.id.radio_szbbs)
    RadioButton radioSzbbs;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private NewsFragment newsFragment;
    private SemartCityFragment smartcityFragment;
    private BBSFragment szbbsFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //自定义底部radiobutton图片大小和位置
        changeImageSize();

        //初始化并设置当前Fragment
        //initFragment(0);

        fragmentManager = getSupportFragmentManager();
        radioGroup.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        radioNews = (RadioButton) findViewById(R.id.radio_news);
        radioNews.setChecked(true);
    }

    private void changeImageSize() {
        //定义底部标签图片大小
        Drawable tab_news = getResources().getDrawable(R.drawable.tab_selector_news);
        tab_news.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioNews.setCompoundDrawables(null, tab_news, null, null);//只放上面

        Drawable tab_service = getResources().getDrawable(R.drawable.tab_selector_smartcity);
        tab_service.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioSmartcity.setCompoundDrawables(null, tab_service, null, null);//只放上面

        Drawable tab_hudong = getResources().getDrawable(R.drawable.tab_selector_smartcity);
        tab_hudong.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioSzbbs.setCompoundDrawables(null, tab_hudong, null, null);//只放上面
    }

//    private void initFragment(int index) {
//        fragmentManager = getSupportFragmentManager();
//        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        ((RadioButton) radioGroup.findViewById(R.id.radio_news)).setChecked(true);
//        //开启事务
//        transaction = fragmentManager.beginTransaction();
//        Fragment mfragment = new NewsFragment();
//        transaction.replace(R.id.Framegment, mfragment);
//        //提交事务
//        transaction.commit();
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                fragmentManager = getSupportFragmentManager();
//                transaction = fragmentManager.beginTransaction();
//                hideFragment(transaction);
//                switch (checkedId) {
//                    case R.id.radio_news:
//                        transaction = fragmentManager.beginTransaction();
//                        Fragment newsFragment = new NewsFragment();
//                        transaction.replace(R.id.Framegment, newsFragment);
//                        transaction.commit();
//                        break;
//                    case R.id.radio_smartcity:
//                        transaction = fragmentManager.beginTransaction();
//                        Fragment smartcityFragment = new SemartCityFragment();
//                        transaction.replace(R.id.Framegment, smartcityFragment);
//                        transaction.commit();
//                        break;
//                    case R.id.radio_szbbs:
//                        transaction = fragmentManager.beginTransaction();
//                        Fragment szbbsFragment = new BBSFragment();
//                        transaction.replace(R.id.Framegment, szbbsFragment);
//                        transaction.commit();
//                        break;
//                }
//
//            }
//        });
//
//    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        android.support.v4.app.FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideFragment(fTransaction);
        switch (checkedId){
            case R.id.radio_news:
                if(newsFragment == null){
                    newsFragment = new NewsFragment();
                    fTransaction.add(R.id.Framegment,newsFragment);
                }else{
                    fTransaction.show(newsFragment);
                }
                break;
            case R.id.radio_smartcity:
                if(smartcityFragment == null){
                    smartcityFragment = new SemartCityFragment();
                    fTransaction.add(R.id.Framegment,smartcityFragment);
                }else{
                    fTransaction.show(smartcityFragment);
                }
                break;
            case R.id.radio_szbbs:
                if(szbbsFragment == null){
                    szbbsFragment = new BBSFragment();
                    fTransaction.add(R.id.Framegment,szbbsFragment);
                }else{
                    fTransaction.show(szbbsFragment);
                }
                break;
        }
        fTransaction.commit();
    }


    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (smartcityFragment != null) {
            transaction.hide(smartcityFragment);
        }
        if (szbbsFragment != null) {
            transaction.hide(szbbsFragment);
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {                                                    //两次按键小于2秒时，退出应用
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
