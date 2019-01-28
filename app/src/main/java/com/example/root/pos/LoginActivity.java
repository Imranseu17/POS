package com.example.root.pos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.root.pos.callbacks.LoginView;
import com.example.root.pos.databinding.ActivityLoginBinding;
import com.example.root.pos.dialogs.CustomAlertDialog;
import com.example.root.pos.model.LoginPage;
import com.example.root.pos.model.SharedDataSaveLoad;
import com.example.root.pos.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    ActivityLoginBinding activityLoginBinding;
    LoginPresenter loginPresenter;

    @BindView(R.id.username)
    TextInputEditText userName;

    @BindView(R.id.password)
    TextInputEditText passwordText;

    @BindView(R.id.login)
    AppCompatButton login;


    public void onLogin(){
        boolean response = checkEditTextIsEmptyOrNot();

        if(response){
            submitLogin();
        }
        else CustomAlertDialog.showError(this,"Please fill the form first");

    }

    public void onCreateAccount(){

        startActivity(new Intent(LoginActivity.this, CreateAccount.class));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        activityLoginBinding.setLogin(new LoginPage(getString(R.string.username),getString(R.string.password)));
        activityLoginBinding.setActivity(this);


    }

    private void submitLogin() {


        String username = userName.getText().toString().trim();
        SharedDataSaveLoad.save(this,getString(R.string.preference_user_name),username);
        hideKeyboard(this);
        getLogin();

    }

    private void getLogin() {

        if (checkConnection()) {
            String username = userName.getText().toString().trim();
            String password = passwordText.getText().toString().trim();
                loginPresenter.login(username,password);
        } else CustomAlertDialog.showError(this,getString(R.string.no_internet_connection));
    }



    @Override
    public void onLogin(String message) {
        startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));
        CustomAlertDialog.showSuccess(this,message);
    }

    @Override
    public void onError(String error) {
        CustomAlertDialog.showError(this,error);

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private boolean checkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public  boolean checkEditTextIsEmptyOrNot() {
        String EmailHolder, PasswordHolder;


        EmailHolder = userName.getText().toString().trim();
        PasswordHolder = passwordText.getText().toString().trim();

        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {


            return false;

        } else {

            return true;
        }
    }



}
