package com.example.root.pos;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.root.pos.databinding.ActivitySettingsBinding;
import com.example.root.pos.model.ChooseAlertDialog;
import com.example.root.pos.model.LanguageCustomDialog;
import com.example.root.pos.model.PromptDialog;
import com.example.root.pos.model.Settings;
import com.example.root.pos.model.SharedDataSaveLoad;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements LanguageSelectListener {

    ActivitySettingsBinding settingsBinding;

     LanguageCustomDialog mLanguageDialog;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layout_language)
    RelativeLayout language;

    @BindView(R.id.layout_signout)
    RelativeLayout signOut;

    @BindView(R.id.txt_language)
    TextView txt_language;

    @BindView(R.id.txt_signout)
    TextView txt_signOut;

    @BindView(R.id.img_language)
    ImageView img_language;

    @BindView(R.id.img_signout)
    ImageView  img_signout;

    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsBinding = DataBindingUtil.setContentView(this,R.layout.activity_settings);
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        closeOptionsMenu();
        setTitle("  Settings");
        mLanguageDialog = new LanguageCustomDialog();
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_clear_all_black_24dp);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        settingsBinding.setSettings(new Settings(" Language",
                " Sign Out"));

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguageDialog.showDialog(SettingsActivity.this, SettingsActivity.this);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });
    }

    @Override
    public void onLanguageSelect(String lang) {
        setLanguage(lang);
    }

    private void setLanguage(String lang) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang.toLowerCase())); // API 17+ only.
        res.updateConfiguration(conf, dm);
        SharedDataSaveLoad.save(this, getString(R.string.preference_language_key), lang);
        restartActivity();
    }

    private void restartActivity() {
        Intent setting = getIntent();
        Intent home = DashBoardActivity.dashBoardActivity.getIntent();

        DashBoardActivity.dashBoardActivity.finish();
        SettingsActivity.this.finish();

        startActivity(home);
        startActivity(setting);
    }

    public void showLogoutDialog() {
        new ChooseAlertDialog(SettingsActivity.this)
                .setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                .setAnimationEnable(true)
                .setTitleText(getString(R.string.logout))
                .setContentText(getString(R.string.info_want_to_logout))
                .setNegativeListener(getString(R.string.cancel), new ChooseAlertDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ChooseAlertDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .setPositiveListener(getString(R.string.yes), new ChooseAlertDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ChooseAlertDialog dialog) {
                        dialog.dismiss();
                        logoutActivity();
                    }
                }).show();
    }

    private void logoutActivity() {
        SharedDataSaveLoad.remove(SettingsActivity.this, getString(R.string.preference_access_token));
        SharedDataSaveLoad.remove(this, getString(R.string.preference_is_service_check));
        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
