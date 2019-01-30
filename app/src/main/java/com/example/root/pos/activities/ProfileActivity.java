package com.example.root.pos.activities;


import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.root.pos.R;
import com.example.root.pos.callbacks.LoginView;
import com.example.root.pos.callbacks.ProfileView;
import com.example.root.pos.databinding.ActivityProfileBinding;
import com.example.root.pos.dialogs.CustomAlertDialog;
import com.example.root.pos.model.Profile;
import com.example.root.pos.model.SharedDataSaveLoad;
import com.example.root.pos.model.UserData;
import com.example.root.pos.presenter.ProfilePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileView {


    ActivityProfileBinding profileBinding;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imgCricle)
    ImageView imageCricle;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.email)
    TextView username;

    @BindView(R.id.phoneNumber)
    TextView phoneNumber;

    @BindView(R.id.occupationnname)
    TextView occupationname;

    @BindView(R.id.address)
    TextView address;

    ProfilePresenter profilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        ButterKnife.bind(this);
        profilePresenter = new ProfilePresenter(this);
        setTitle(getString(R.string.profile));
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_clear_all_black_24dp);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        Glide.with(this)
                .load(R.drawable.imran)
                .apply(RequestOptions.circleCropTransform())
                .into(imageCricle);


        profilePresenter.profile(SharedDataSaveLoad.load(this,getString(R.string.preference_user_name)));
    }

    @Override
    public void onSuccess(UserData userData) {
        profileBinding.setProfile(new Profile(userData.getName(),userData.getUsername(),
                userData.getPhonenumber(),userData.getOccupationnname(),
                userData.getAddress()));

        SharedDataSaveLoad.saveInt(this,"userID",userData.getId());
    }

    @Override
    public void onError(String error) {

        CustomAlertDialog.showError(this,error);
    }
}
