package com.example.root.pos.presenter;

import com.example.root.pos.callbacks.ProfileView;
import com.example.root.pos.errors.APIErrors;
import com.example.root.pos.model.UserData;
import com.example.root.pos.services.APIClient;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class ProfilePresenter {

    private ProfileView mViewInterface;
    private APIClient mApiClient;

    public ProfilePresenter(ProfileView view) {
        this.mViewInterface = view;

        if (this.mApiClient == null) {
            this.mApiClient = new APIClient();
        }
    }

    public void profile(String  username) {
        

      

        mApiClient.getAPI()
                .onProfile(username)
                .enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {


                        if (response.isSuccessful()) {
                            UserData UserData = response.body();
                            if (UserData != null) {
                                mViewInterface.onSuccess(UserData);
                            } else {
                                mViewInterface.onError("Error fetching data");
                            }
                        } else errorHandle(response.code(), response.errorBody());


                    }

                    @Override
                    public void onFailure(Call<UserData> call, Throwable e) {
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
