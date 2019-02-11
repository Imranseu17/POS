package com.example.root.pos.activities;


import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

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

    private static final int SELECT_PHOTO = 1;

    ProfilePresenter profilePresenter;


    public void onUpdateAccount(){

        startActivity(new Intent(ProfileActivity.this,UpdateActivity.class));
    }

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

        profilePresenter.profile(SharedDataSaveLoad.load(this,getString(R.string.preference_user_name)));

        profileBinding.setActivity(this);


        String previouslyEncodedImage = SharedDataSaveLoad.load(this,getString(R.string.imagePath));

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
           Glide.with(this)
                   .load(bitmap)
                   .apply(RequestOptions.circleCropTransform())
                   .into(imageCricle);
        }




    }

    public void onShowImage(){
        openGallery();


    }

    @Override
    public void onSuccess(UserData userData) {
        profileBinding.setProfile(new Profile(userData.getName(),userData.getUsername(),
                userData.getPhonenumber(),userData.getOccupationnname(),
                userData.getAddress()));

        SharedDataSaveLoad.saveInt(this,getString(R.string.userID),userData.getId());
        SharedDataSaveLoad.save(this,getString(R.string.name),userData.getName());
        SharedDataSaveLoad.save(this,getString(R.string.passwordSave),userData.getPassword());
        SharedDataSaveLoad.save(this,getString(R.string.confirmpassword),userData.getConfirmpassword());
        SharedDataSaveLoad.save(this,getString(R.string.address),userData.getAddress());
        SharedDataSaveLoad.save(this,getString(R.string.phonenumber),userData.getPhonenumber());
        SharedDataSaveLoad.save(this,getString(R.string.occupationname),userData.getOccupationnname());
    }

    @Override
    public void onError(String error) {

        CustomAlertDialog.showError(this,error);
    }

    private void openGallery(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case SELECT_PHOTO:
                if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && null != data){
                    Uri selectedImage = data.getData();
                    imageCricle.setImageURI(selectedImage);
                    try {
                        Bitmap thumbnail = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                selectedImage);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] b = baos.toByteArray();
                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        SharedDataSaveLoad.save(this,getString(R.string.imagePath),encodedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
        }
    }






}
