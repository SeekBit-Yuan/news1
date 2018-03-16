package com.sznews.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sznews.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiy on 2018-3-15.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.login_quit)
    ImageView loginQuit;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_forgetpw)
    TextView loginForgetpw;
    @BindView(R.id.login_weixin)
    ImageView loginWeixin;
    @BindView(R.id.login_qq)
    ImageView loginQq;
    @BindView(R.id.login_weibo)
    ImageView loginWeibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();

    }

    public void initView() {
        MyTextWatcher myTextWatcher = new MyTextWatcher();

        username.addTextChangedListener(myTextWatcher);
        password.addTextChangedListener(myTextWatcher);

        loginQuit.setOnClickListener(this);
        loginRegister.setOnClickListener(this);
        loginForgetpw.setOnClickListener(this);
        loginWeixin.setOnClickListener(this);
        loginQq.setOnClickListener(this);
        loginWeibo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_quit:
                finish();
                break;
            case R.id.login_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_forgetpw:
                Toast.makeText(LoginActivity.this, getString(R.string.login_forgetpassword), Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_weixin:
                Toast.makeText(LoginActivity.this, getString(R.string.login_weixin), Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_qq:
                Toast.makeText(LoginActivity.this, getString(R.string.login_qq), Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_weibo:
                Toast.makeText(LoginActivity.this, getString(R.string.login_weibo), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //监听多个EditText
    public class MyTextWatcher implements TextWatcher {

        /**
         * 文本输入改变之前调用（还未改变）
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         * 文本改变过程中调用（文本替换动作）
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        /**
         * 文本改标之后调用（文本已经替换完成）
         */
        @Override
        public void afterTextChanged(Editable s) {
            if (username.length() > 0 && password.length() > 0) {
                signInButton.setBackground(getResources().getDrawable(R.drawable.login_button_change));
            }else if(username.length() == 0 || password.length() == 0) {
                signInButton.setBackground(getResources().getDrawable(R.drawable.login_button_shape));
            }
        }
    }


}
