package com.example.root.pos;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;

import com.example.root.pos.databinding.ActivityCreateAccountBinding;

import com.example.root.pos.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccount extends Activity {

    ActivityCreateAccountBinding activityCreateAccountBinding;

    @BindView(R.id.name)
    TextInputLayout name;

    @BindView(R.id.username)
    TextInputLayout username;

    @BindView(R.id.address)
    TextInputLayout address;

    @BindView(R.id.password)
    TextInputLayout password;

    @BindView(R.id.confirmPassword)
    TextInputLayout confirmPassword;

    @BindView(R.id.submit)
    AppCompatButton submit;

    public void onSubmit(){


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateAccountBinding = DataBindingUtil.setContentView(this,R.layout.activity_create_account);
        ButterKnife.bind(this);
        activityCreateAccountBinding.setUser(new User("Name","UserName","Address","Password","ConfirmPassword"));

    }
}
