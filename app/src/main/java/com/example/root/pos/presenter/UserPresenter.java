package com.example.root.pos.presenter;

import com.example.root.pos.callbacks.UserView;
import com.example.root.pos.errors.APIErrors;
import com.example.root.pos.services.APIClient;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class UserPresenter {

    private UserView mViewInterface;
    private APIClient mApiClient;

    public UserPresenter(UserView view) {
        this.mViewInterface = view;

        if (this.mApiClient == null) {
            this.mApiClient = new APIClient();
        }
    }

    public void onsaveUser(String name, String username,
                           String password, String confirmpassword,
                           String address) {


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("confirmpassword", confirmpassword);
        jsonObject.addProperty("address", address);



        mApiClient.getAPI()
                .saveUser(jsonObject)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                        if (response.isSuccessful()) {
                            try {
                                if (response.body().string().contains("Save Successfully")) {
                                    mViewInterface.onSuccess("Save Successfully");
                                }else
                                    mViewInterface.onError("This username already exits");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else mViewInterface.onError("Save Failed");


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            int code = ((HttpException) e).response().code();
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            errorHandle(code, responseBody);

                        } else if (e instanceof SocketTimeoutException) {

                            mViewInterface.onError("Server connection error");
                        } else if (e instanceof IOException) {
                            mViewInterface.onError("IOException");
                        } else {
                            mViewInterface.onError("Unknown exception");
                        }
                    }
                });
    }

    private void errorHandle(int code, ResponseBody responseBody){
        if (code == 500) mViewInterface.onError(APIErrors.get500ErrorMessage(responseBody));
        else if(code == 406){
            try {
                JSONObject jObjError = new JSONObject(responseBody.string());
                mViewInterface.onError(jObjError.getString("message"));
            } catch (Exception e) {
                mViewInterface.onError(e.getMessage());
            }
        }
        else mViewInterface.onError(APIErrors.getErrorMessage(responseBody));
    }


}
