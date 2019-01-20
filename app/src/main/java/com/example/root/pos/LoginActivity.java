package com.example.root.pos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;


import com.example.root.pos.databinding.ActivityLoginBindingImpl;
import com.example.root.pos.model.LoginPage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBindingImpl activityLoginBinding;

    @BindView(R.id.userName)
    TextInputLayout userName;

    @BindView(R.id.password)
    TextInputLayout password;

    @BindView(R.id.login)
    AppCompatButton login;


    public void onLogin(){
        startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));
    }

    public void onCreateAccount(){

        startActivity(new Intent(LoginActivity.this,CreateAccountActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        ButterKnife.bind(this);
        activityLoginBinding.setLogin(new LoginPage("USERNAME","PASSWORD"));
        activityLoginBinding.setActivity(this);
    }
}
