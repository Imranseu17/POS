package com.example.root.pos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.root.pos.activities.HistoryActivity;
import com.example.root.pos.activities.LocationActivity;
import com.example.root.pos.activities.ProfileActivity;
import com.example.root.pos.activities.SaleActivity;
import com.example.root.pos.activities.SearchActivity;
import com.example.root.pos.databinding.ActivityDashBoardBinding;

import com.example.root.pos.model.Home;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardActivity extends AppCompatActivity

{

    public static DashBoardActivity  dashBoardActivity;

    ActivityDashBoardBinding home ;

    @BindView(R.id.product_sale)
    CardView productsale;

    @BindView(R.id.profile)
    CardView profile;

    @BindView(R.id.location)
    CardView location;

    @BindView(R.id.search)
    CardView search;

    @BindView(R.id.sales_history)
    CardView sales_history;

    @BindView(R.id.line_sale)
    View sale;

    @BindView(R.id.line_profile)
    View profileline;

    @BindView(R.id.line_location)
    View lineLocatin;

    @BindView(R.id.line_search)
    View lineSearch;

    @BindView(R.id.line_history)
    View history;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        home = DataBindingUtil.setContentView(this,R.layout.activity_dash_board);

        ButterKnife.bind(this);
        setTitle(R.string.home);

        dashBoardActivity = this;

        home.setHome(new Home(getString(R.string.productSale), getString(R.string.profile),
                getString(R.string.location),getString(R.string.search),getString(R.string.sales_history)));

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_clear_all_black_24dp);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);






        productsale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sale.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.green));
                profileline.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineLocatin.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineSearch.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                history.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));

                startActivity(new Intent(DashBoardActivity.this, SaleActivity.class));


            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sale.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                profileline.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.green));
                lineLocatin.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineSearch.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                history.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));

                startActivity(new Intent(DashBoardActivity.this, ProfileActivity.class));
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sale.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                profileline.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineLocatin.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.green));
                lineSearch.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                history.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));

                startActivity(new Intent(DashBoardActivity.this, LocationActivity.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sale.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                profileline.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineLocatin.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineSearch.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.green));
                history.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));

                startActivity(new Intent(DashBoardActivity.this, SearchActivity.class));
            }
        });

        sales_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sale.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                profileline.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineLocatin.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineSearch.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                history.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.green));

                startActivity(new Intent(DashBoardActivity.this, HistoryActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_setting) {
            startActivity(new Intent(DashBoardActivity.this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
