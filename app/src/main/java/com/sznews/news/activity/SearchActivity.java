package com.sznews.news.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sznews.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiy on 2018-3-16.
 */

public class SearchActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.serach)
    EditText serach;
    @BindView(R.id.search_cancel)
    TextView searchCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initView();

    }

    public void initView() {

        searchCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_cancel:
                finish();
                break;
        }
    }
}
