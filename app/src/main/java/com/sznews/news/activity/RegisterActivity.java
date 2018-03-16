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

import com.sznews.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiy on 2018-3-16.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.register_back)
    ImageView registerBack;
    @BindView(R.id.register_username)
    EditText registerUsername;
    @BindView(R.id.register_mobile)
    EditText registerMobile;
    @BindView(R.id.register_verification)
    EditText registerVerification;
    @BindView(R.id.register_verification_send)
    TextView registerVerificationSend;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.sign_up_button)
    Button signUpButton;
    @BindView(R.id.register_login)
    TextView registerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initView();

    }

    public void initView() {

        MyTextWatcher myTextWatcher = new MyTextWatcher();

        registerUsername.addTextChangedListener(myTextWatcher);
        registerMobile.addTextChangedListener(myTextWatcher);
        registerVerification.addTextChangedListener(myTextWatcher);
        registerPassword.addTextChangedListener(myTextWatcher);

        registerBack.setOnClickListener(this);
        registerLogin.setOnClickListener(this);
        registerVerificationSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_back:
                finish();
                break;
            case R.id.register_login:
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.register_verification_send:
                registerVerificationSend.setText(getResources().getString(R.string.register_verification_sended));
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
            if (registerUsername.length() > 0 && registerMobile.length() > 0 && registerVerification.length() > 0 && registerPassword.length() > 0) {
                signUpButton.setBackground(getResources().getDrawable(R.drawable.login_button_change));
            }else if(registerUsername.length() == 0 || registerMobile.length() == 0 || registerVerification.length() == 0 || registerPassword.length() == 0) {
                signUpButton.setBackground(getResources().getDrawable(R.drawable.login_button_shape));
            }
        }
    }
}
