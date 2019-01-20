package com.example.root.pos;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardActivity extends AppCompatActivity

{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ButterKnife.bind(this);
        setTitle("     Home");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        productsale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sale.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.green));
                profileline.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineLocatin.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                lineSearch.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));
                history.setBackgroundColor(ContextCompat.getColor(DashBoardActivity.this, R.color.black_overlay));


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
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
