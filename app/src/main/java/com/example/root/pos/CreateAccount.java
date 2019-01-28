package com.example.root.pos;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.root.pos.callbacks.UserView;
import com.example.root.pos.databinding.ActivityCreateAccountBinding;
import com.example.root.pos.dialogs.CustomAlertDialog;
import com.example.root.pos.model.User;
import com.example.root.pos.model.UserData;
import com.example.root.pos.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccount extends Activity implements UserView {

    ActivityCreateAccountBinding activityCreateAccountBinding;

    @BindView(R.id.name)
    TextInputEditText name;

    @BindView(R.id.username)
    TextInputEditText username;

    @BindView(R.id.address)
    TextInputEditText address;

    @BindView(R.id.password)
    TextInputEditText password;

    @BindView(R.id.confirmPassword)
    TextInputEditText confirmPassword;

    @BindView(R.id.submit)
    AppCompatButton submit;

    private UserPresenter userPresenter;

    public void onSubmit(){

            boolean response = checkEditTextIsEmptyOrNot();

            if(response){


            if(password.getText().toString().trim().equals(
                    confirmPassword.getText().toString().trim())){
                getData();
            }
            else {
                CustomAlertDialog.showError(CreateAccount.this,"Password Does not matched");
            }
        }

        else {
            CustomAlertDialog.showError(this,"Please fill the form first");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateAccountBinding = DataBindingUtil.setContentView(this,R.layout.activity_create_account);
        ButterKnife.bind(this);
        userPresenter = new UserPresenter(this);
        activityCreateAccountBinding.setActivity(this);
        activityCreateAccountBinding.setUser(new User("Name","UserName","Address","Password","ConfirmPassword"));

    }

    @Override
    public void onSuccess(String success) {

        CustomAlertDialog.showSuccess(CreateAccount.this,success);
        name.setText("");
        username.setText("");
        password.setText("");
        confirmPassword.setText("");
        address.setText("");

    }

    @Override
    public void onError(String error) {
        CustomAlertDialog.showError(CreateAccount.this,error);
    }

    private void getData() {

        if (checkConnection()) {
            String nameInput = name.getText().toString().trim();
            String usernameInput = username.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            String confirmpasswordInput = confirmPassword.getText().toString().trim();
            String addressInput = address.getText().toString().trim();
            userPresenter.onsaveUser(nameInput,usernameInput,passwordInput,confirmpasswordInput,addressInput);
        } else CustomAlertDialog.showError(this,getString(R.string.no_internet_connection));
    }



    private boolean checkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public  boolean checkEditTextIsEmptyOrNot() {

        String nameInput = name.getText().toString().trim();
        String usernameInput = username.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();
        String confirmpasswordInput = confirmPassword.getText().toString().trim();
        String addressInput = address.getText().toString().trim();

        if (TextUtils.isEmpty(nameInput) || TextUtils.isEmpty(usernameInput)
                || TextUtils.isEmpty(passwordInput) || TextUtils.isEmpty(confirmpasswordInput)
                || TextUtils.isEmpty(addressInput)) {

            return false;

        } else {

            return true;
        }
    }











}
