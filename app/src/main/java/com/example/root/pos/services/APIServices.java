package com.example.root.pos.services;




import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServices {

    @POST("/auth/login/{username}/{password}")
    Call<ResponseBody> login(@Path("username") String username,
                             @Path("password") String password);

}
