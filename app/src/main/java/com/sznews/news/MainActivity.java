package com.sznews.news;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sznews.news.activity.DigitalNewspaperActivity;
import com.sznews.news.activity.LoginActivity;
import com.sznews.news.activity.RegisterActivity;
import com.sznews.news.activity.SearchActivity;
import com.sznews.news.fragment.BBSFragment;
import com.sznews.news.fragment.GovernmentFragment;
import com.sznews.news.fragment.NewsFragment;
import com.sznews.news.fragment.NewsPaperFragment;
import com.sznews.news.fragment.SmartCityFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{


    @BindView(R.id.imageView_login)
    ImageView imageViewLogin;
    @BindView(R.id.imageView_logo)
    ImageView imageViewLogo;
    @BindView(R.id.imageView_search)
    ImageView imageViewSearch;
    @BindView(R.id.tool_bar1)
    LinearLayout toolBar1;
    @BindView(R.id.Framegment)
    FrameLayout Framegment;
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

    private static boolean enableNightMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //判断是否是夜间模式
        if(!enableNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

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
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
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
        radioNewspaper.setCompoundDrawables(null, tab_news, null, null);//只放上面

        Drawable tab_government = getResources().getDrawable(R.drawable.tab_selector_government);
        tab_news.setBounds(0, 0, 96, 64);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioGovernment.setCompoundDrawables(null, tab_news, null, null);//只放上面

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
                }

                sideslip.closeDrawer(nav);
                return true;
            }
        });

        RadioButton radioCollection = headerView.findViewById(R.id.sideslip_collection);
        RadioButton radioHistory = headerView.findViewById(R.id.sideslip_history);
        final RadioButton radioModel = headerView.findViewById(R.id.sideslip_model);

        ImageView imageLogin = headerView.findViewById(R.id.login);
        TextView textGegister = headerView.findViewById(R.id.sideslip_head_register);
        TextView textLogin = headerView.findViewById(R.id.sideslip_head_login);

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
                if(radioModel.isChecked() && !enableNightMode){
                    setEnableNightMode(true);
                }else if(radioModel.isChecked() && enableNightMode){
                    setEnableNightMode(false);
                }
                sideslip.closeDrawer(nav);
            }
        });

        radioHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.sideslip_history), Toast.LENGTH_SHORT).show();
                sideslip.closeDrawer(nav);
            }
        });

        imageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                sideslip.closeDrawer(nav);
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                sideslip.closeDrawer(nav);
            }
        });

        textGegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                sideslip.closeDrawer(nav);
            }
        });
    }

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

    /**
     * enable night mode or not
     * @param enableNightMode   true or false
     */
    public void setEnableNightMode(boolean enableNightMode) {
        this.enableNightMode = enableNightMode;
        if(enableNightMode) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

}
