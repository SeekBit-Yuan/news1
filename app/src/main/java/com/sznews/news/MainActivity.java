package com.sznews.news;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sznews.news.activity.DigitalNewspaperActivity;
import com.sznews.news.fragment.BBSFragment;
import com.sznews.news.fragment.GovernmentFragment;
import com.sznews.news.fragment.NewsFragment;
import com.sznews.news.fragment.NewsPaperFragment;
import com.sznews.news.fragment.SmartCityFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.radio_news)
    RadioButton radioNews;
    @BindView(R.id.radio_newspaper)
    RadioButton radioNewspaper;
    @BindView(R.id.radio_government)
    RadioButton radioGovernment;
    @BindView(R.id.radio_smartcity)
    RadioButton radioSmartcity;
    @BindView(R.id.radio_szbbs)
    RadioButton radioSzbbs;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.sideslip)
    DrawerLayout sideslip;
    @BindView(R.id.imageView_login)
    ImageView imageViewLogin;
    @BindView(R.id.imageView_search)
    ImageView imageViewSearch;

    private NewsFragment newsFragment;
    private NewsPaperFragment newsPaperFragment;
    private GovernmentFragment governmentFragment;
    private SmartCityFragment smartcityFragment;
    private BBSFragment szbbsFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat format2 = new SimpleDateFormat("dd");
    String ym = format1.format(new Date());
    String d = format2.format(new Date());

    private final String DIGITAL_NEWSPAPER_URL1 = "http://sztqb.sznews.com/MB/layout/" + ym + "/" + d + "/colA01.html";
    private final String DIGITAL_NEWSPAPER_URL2 = "http://szsb.sznews.com//MB/layout/" + ym + "/" + d + "/colA01.html";
    private final String DIGITAL_NEWSPAPER_URL3 = "http://wb.sznews.com/MB/layout/" + ym + "/" + d + "/colA01.html";
    private final String DIGITAL_NEWSPAPER_URL4 = "http://jb.sznews.com/MB/layout/" + ym + "/" + d + "/colA01.html";
    private final String DIGITAL_NEWSPAPER_URL5 = "http://barb.sznews.com/MB/layout/" + ym + "/" + d + "/colA01.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //自定义底部radiobutton图片大小和位置
//        changeImageSize();
        //侧滑栏
        sideslip();

        //初始化并设置当前Fragment
        //initFragment(0);

        fragmentManager = getSupportFragmentManager();
        radioGroup.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        radioNews = (RadioButton) findViewById(R.id.radio_news);
        radioNews.setChecked(true);

        imageViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sideslip.isDrawerOpen(nav)) {
                    sideslip.closeDrawer(nav);
                } else {
                    sideslip.openDrawer(nav);
                }
            }
        });

        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this,"搜索", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeImageSize() {
        //定义底部标签图片大小
        Drawable tab_news = getResources().getDrawable(R.drawable.tab_selector_news);
        tab_news.setBounds(0, 0, 96, 64);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioNews.setCompoundDrawables(null, tab_news, null, null);//只放上面

        Drawable tab_newspaper = getResources().getDrawable(R.drawable.tab_selector_newspaper);
        tab_news.setBounds(0, 0, 96, 64);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioNews.setCompoundDrawables(null, tab_news, null, null);//只放上面

        Drawable tab_government = getResources().getDrawable(R.drawable.tab_selector_government);
        tab_news.setBounds(0, 0, 96, 64);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioNews.setCompoundDrawables(null, tab_news, null, null);//只放上面

        Drawable tab_service = getResources().getDrawable(R.drawable.tab_selector_smartcity);
        tab_service.setBounds(0, 0, 96, 64);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioSmartcity.setCompoundDrawables(null, tab_service, null, null);//只放上面

        Drawable tab_szbbs = getResources().getDrawable(R.drawable.tab_selector_szbbs);
        tab_szbbs.setBounds(0, 0, 96, 64);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioSzbbs.setCompoundDrawables(null, tab_szbbs, null, null);//只放上面
    }

    //侧滑栏
    private void sideslip() {
        View headerView = nav.getHeaderView(0);//获取头布局
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.np1) {
                    Intent intent = new Intent(MainActivity.this, DigitalNewspaperActivity.class);
                    intent.putExtra("digitalnewspaper_url", DIGITAL_NEWSPAPER_URL1);
                    startActivity(intent);
                } else if (id == R.id.np2) {
                    Intent intent = new Intent(MainActivity.this, DigitalNewspaperActivity.class);
                    intent.putExtra("digitalnewspaper_url", DIGITAL_NEWSPAPER_URL2);
                    startActivity(intent);
                } else if (id == R.id.np3) {
                    Intent intent = new Intent(MainActivity.this, DigitalNewspaperActivity.class);
                    intent.putExtra("digitalnewspaper_url", DIGITAL_NEWSPAPER_URL3);
                    startActivity(intent);
                } else if (id == R.id.np4) {
                    Intent intent = new Intent(MainActivity.this, DigitalNewspaperActivity.class);
                    intent.putExtra("digitalnewspaper_url", DIGITAL_NEWSPAPER_URL4);
                    startActivity(intent);
                } else if (id == R.id.np5) {
                    Intent intent = new Intent(MainActivity.this, DigitalNewspaperActivity.class);
                    intent.putExtra("digitalnewspaper_url", DIGITAL_NEWSPAPER_URL5);
                    startActivity(intent);
                }

                sideslip.closeDrawer(nav);
                return true;
            }
        });

        RadioButton radioCollection = headerView.findViewById(R.id.sideslip_collection);
        RadioButton radioModel = headerView.findViewById(R.id.sideslip_model);
        RadioButton radioSetting = headerView.findViewById(R.id.sideslip_setting);
        ImageView imageLogin = headerView.findViewById(R.id.login);
        TextView textLogin = headerView.findViewById(R.id.sideslip_headtitle);

        Drawable tab_collection = getResources().getDrawable(R.drawable.tab_selector_sideslip_collection);
        tab_collection.setBounds(0, 0, 60, 60);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioCollection.setCompoundDrawables(null, tab_collection, null, null);//只放上面

        Drawable tab_model = getResources().getDrawable(R.drawable.tab_selector_sideslip_model);
        tab_model.setBounds(0, 0, 60, 60);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioModel.setCompoundDrawables(null, tab_model, null, null);//只放上面

        Drawable tab_setting = getResources().getDrawable(R.drawable.tab_selector_sideslip_setting);
        tab_setting.setBounds(0, 0, 60, 60);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioSetting.setCompoundDrawables(null, tab_setting, null, null);//只放上面

        radioCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.sideslip_collection), Toast.LENGTH_SHORT).show();
                sideslip.closeDrawer(nav);
            }
        });

        radioModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.sideslip_model), Toast.LENGTH_SHORT).show();
                sideslip.closeDrawer(nav);
            }
        });

        radioSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.sideslip_setting), Toast.LENGTH_SHORT).show();
                sideslip.closeDrawer(nav);
            }
        });

        imageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.sideslip_headtitle), Toast.LENGTH_SHORT).show();
                sideslip.closeDrawer(nav);
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.sideslip_headtitle), Toast.LENGTH_SHORT).show();
                sideslip.closeDrawer(nav);
            }
        });
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
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideFragment(fTransaction);
        switch (checkedId) {
            case R.id.radio_news:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    fTransaction.add(R.id.Framegment, newsFragment);
                } else {
                    fTransaction.show(newsFragment);
                }
                break;
            case R.id.radio_newspaper:
                if (newsPaperFragment == null) {
                    newsPaperFragment = new NewsPaperFragment();
                    fTransaction.add(R.id.Framegment, newsPaperFragment);
                } else {
                    fTransaction.show(newsPaperFragment);
                }
                break;
            case R.id.radio_government:
                if (governmentFragment == null) {
                    governmentFragment = new GovernmentFragment();
                    fTransaction.add(R.id.Framegment, governmentFragment);
                } else {
                    fTransaction.show(governmentFragment);
                }
                break;
            case R.id.radio_smartcity:
                if (smartcityFragment == null) {
                    smartcityFragment = new SmartCityFragment();
                    fTransaction.add(R.id.Framegment, smartcityFragment);
                } else {
                    fTransaction.show(smartcityFragment);
                }
                break;
            case R.id.radio_szbbs:
                if (szbbsFragment == null) {
                    szbbsFragment = new BBSFragment();
                    fTransaction.add(R.id.Framegment, szbbsFragment);
                } else {
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
        if (newsPaperFragment != null) {
            transaction.hide(newsPaperFragment);
        }
        if (governmentFragment != null) {
            transaction.hide(governmentFragment);
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

    //侧滑菜单点击事件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_login://点击logo，跳出侧滑菜单
                if (sideslip.isDrawerOpen(nav)) {
                    sideslip.closeDrawer(nav);
                } else {
                    sideslip.openDrawer(nav);
                }
                break;
        }
    }

}
