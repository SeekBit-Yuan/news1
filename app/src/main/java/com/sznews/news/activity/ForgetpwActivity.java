package com.sznews.news.activity;

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

public class ForgetpwActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.forgetpw_back)
    ImageView forgetpwBack;
    @BindView(R.id.forgetpw_title)
    TextView forgetpwTitle;
    @BindView(R.id.forgetpw_mobile)
    EditText forgetpwMobile;
    @BindView(R.id.forgetpw_verification)
    EditText forgetpwVerification;
    @BindView(R.id.register_verification_send)
    TextView registerVerificationSend;
    @BindView(R.id.forgetpw_password)
    EditText forgetpwPassword;
    @BindView(R.id.forgetpw_password_new)
    EditText forgetpwPasswordNew;
    @BindView(R.id.modify_button)
    Button modifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpw);
        ButterKnife.bind(this);

        initView();

    }

    public void initView() {
        MyTextWatcher myTextWatcher = new MyTextWatcher();

        forgetpwMobile.addTextChangedListener(myTextWatcher);
        forgetpwVerification.addTextChangedListener(myTextWatcher);
        forgetpwPassword.addTextChangedListener(myTextWatcher);
        forgetpwPasswordNew.addTextChangedListener(myTextWatcher);

        forgetpwBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forgetpw_back:
                finish();
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
            if (forgetpwMobile.length() > 0 && forgetpwVerification.length() > 0 && forgetpwPassword.length() > 0 && forgetpwPasswordNew.length() > 0) {
                modifyButton.setBackground(getResources().getDrawable(R.drawable.login_button_change));
            }else if(forgetpwMobile.length() == 0 || forgetpwVerification.length() == 0 || forgetpwPassword.length() == 0 || forgetpwPasswordNew.length() == 0) {
                modifyButton.setBackground(getResources().getDrawable(R.drawable.login_button_shape));
            }
        }
    }
}
