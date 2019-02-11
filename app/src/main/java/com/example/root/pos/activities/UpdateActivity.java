package com.example.root.pos.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.pos.CreateAccount;
import com.example.root.pos.R;
import com.example.root.pos.callbacks.UpdateView;
import com.example.root.pos.databinding.ActivityProfileBinding;
import com.example.root.pos.databinding.ActivityUpdateBinding;
import com.example.root.pos.databinding.ActivityUpdateBindingImpl;
import com.example.root.pos.dialogs.CustomAlertDialog;
import com.example.root.pos.model.SharedDataSaveLoad;
import com.example.root.pos.model.User;
import com.example.root.pos.presenter.UpdatePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateActivity extends AppCompatActivity implements UpdateView {

    ActivityUpdateBindingImpl updateBinding;

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

    @BindView(R.id.occupationnname)
    TextInputEditText occupationname;

    @BindView(R.id.phonnumber)
    TextInputEditText phoneNumber;

    @BindView(R.id.submit)
    AppCompatButton submit;

    UpdatePresenter updatePresenter;

    public void onSubmit(){
        boolean response = checkEditTextIsEmptyOrNot();

        if(response){


            if(password.getText().toString().trim().equals(
                    confirmPassword.getText().toString().trim())){
                getData();
            }
            else {
                CustomAlertDialog.showError(this,"Password Does not matched");
            }
        }

        else {
            CustomAlertDialog.showError(this,"Please fill the form first");
        }


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateBinding = DataBindingUtil.setContentView(this,R.layout.activity_update);
        ButterKnife.bind(this);
        updateBinding.setUpdateuser(new User(
                SharedDataSaveLoad.load(this,getString(R.string.name)),
                SharedDataSaveLoad.load(this,getString(R.string.preference_user_name)),
                SharedDataSaveLoad.load(this,getString(R.string.address)),
                SharedDataSaveLoad.load(this,getString(R.string.passwordSave)),
                SharedDataSaveLoad.load(this,getString(R.string.confirmpassword)),
                SharedDataSaveLoad.load(this,getString(R.string.phonenumber)),
                SharedDataSaveLoad.load(this,getString(R.string.occupationname))


        ));

        updatePresenter = new UpdatePresenter(this);
        updateBinding.setActivity(this);
    }

    @Override
    public void onSuccess(String success, int code) {

        CustomAlertDialog.showSuccess(this,success);
        name.setText("");
        username.setText("");
        password.setText("");
        confirmPassword.setText("");
        address.setText("");
        occupationname.setText("");
        phoneNumber.setText("");
    }

    @Override
    public void onError(String error, int code) {

        CustomAlertDialog.showError(this,error);

    }

    private void getData() {

        if (checkConnection()) {
            String nameInput = name.getText().toString().trim();
            String usernameInput = username.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            String confirmpasswordInput = confirmPassword.getText().toString().trim();
            String addressInput = address.getText().toString().trim();
            String occupationnameInput = occupationname.getText().toString().trim();
            String phoneNumberInput = phoneNumber.getText().toString().trim();
            int id = SharedDataSaveLoad.loadInt(this,getString(R.string.userID));

            updatePresenter.update(nameInput,usernameInput,passwordInput,confirmpasswordInput,
                    addressInput,occupationnameInput,phoneNumberInput,id);
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
        String occupationnameInput = occupationname.getText().toString().trim();
        String phoneNumberInput = phoneNumber.getText().toString().trim();

        if (TextUtils.isEmpty(nameInput) || TextUtils.isEmpty(usernameInput)
                || TextUtils.isEmpty(passwordInput) || TextUtils.isEmpty(confirmpasswordInput)
                || TextUtils.isEmpty(addressInput) || TextUtils.isEmpty(occupationnameInput)
                || TextUtils.isEmpty(phoneNumberInput)) {

            return false;

        } else {

            return true;
        }
    }
}
