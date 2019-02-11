package com.example.root.pos.activities;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.root.pos.R;
import com.example.root.pos.adapter.ViewPagerAdapter;
import com.example.root.pos.fragment.NewOrderFragment;
import com.example.root.pos.fragment.PreviousOrderFragment;
import com.example.root.pos.fragment.ProductFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        setTitle(" Product  Sale ");
        ButterKnife.bind(this);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_clear_all_black_24dp);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductFragment(), "Product");
        adapter.addFragment(new PreviousOrderFragment(), "Previous Order");
        adapter.addFragment(new NewOrderFragment(), "New Order");
        viewPager.setAdapter(adapter);
    }

}
