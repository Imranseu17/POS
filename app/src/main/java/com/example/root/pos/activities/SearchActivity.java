package com.example.root.pos.activities;


import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;

import com.example.root.pos.R;
import com.example.root.pos.databinding.ActivitySearchBindingImpl;
import com.example.root.pos.model.Search;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBindingImpl activitySearchBinding;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.productName)
    TextInputLayout productName;

    @BindView(R.id.shopAddress)
    TextInputLayout shopAddress;

    @BindView(R.id.shopName)
    TextInputLayout shopName;

    @BindView(R.id.salesManPhone)
    TextInputLayout salesManPhone;

    @BindView(R.id.salesManName)
    TextInputLayout salesManName;

    @BindView(R.id.search)
    AppCompatButton search;

    public void onSearch(){


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding = DataBindingUtil.setContentView(this,R.layout.activity_search);
        ButterKnife.bind(this);
        setTitle("  Product  Search");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_clear_all_black_24dp);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);

        activitySearchBinding.setSearch(new Search(" Product Name"," Phone Number", "Shop Name",
                "Shop Address","SalesManName"));

        activitySearchBinding.setSearchActivity(this);

    }
}
