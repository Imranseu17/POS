package com.example.root.pos.services;





import com.bumptech.glide.load.Option;
import com.example.root.pos.model.UserData;
import com.google.android.gms.common.api.Api;
import com.google.gson.JsonObject;

import java.util.Optional;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIServices {

    @POST("/auth/login/{username}/{password}")
    Call<ResponseBody> login(@Path("username") String username,
                             @Path("password") String password);


    @POST("/saveUser")
    Call<ResponseBody> saveUser(@Body JsonObject jsonObject );

    @GET("/findByUser/{username}")
    Call<UserData> onProfile(@Path("username") String username);

    @PUT("/updateusers/{id}")
    Call<Optional<UserData>> UpdateUser(@Path("id") int id, @Body JsonObject jsonObject );


}
