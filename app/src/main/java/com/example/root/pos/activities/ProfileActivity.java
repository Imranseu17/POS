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
import com.example.root.pos.databinding.ActivityProfileBinding;
import com.example.root.pos.model.Profile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {


    ActivityProfileBinding profileBinding;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imgCricle)
    ImageView imageCricle;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.phoneNumber)
    TextView phoneNumber;

    @BindView(R.id.shopName)
    TextView shopName;

    @BindView(R.id.address)
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        ButterKnife.bind(this);
        setTitle(getString(R.string.profile));
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_clear_all_black_24dp);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        Glide.with(this)
                .load(R.drawable.imran)
                .apply(RequestOptions.circleCropTransform())
                .into(imageCricle);

        profileBinding.setProfile(new Profile("Imran Khan","imran1995bd@gmail.com",
                "+8801764310157","Shopno",
                "Nikunju-2, Road-6, House-46, Khilkhet, Dhaka"));

    }
}
