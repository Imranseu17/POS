package com.example.root.pos.presenter;

import android.util.Log;

import com.example.root.pos.callbacks.LoginView;
import com.example.root.pos.errors.APIErrors;

import com.example.root.pos.services.APIClient;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class LoginPresenter {

    private APIClient mApiClient;
    private LoginView mViewInterface;


    public LoginPresenter(LoginView view) {
        this.mViewInterface = view;

        if (this.mApiClient == null) {
            this.mApiClient = new APIClient();
        }
    }




    public void login(final String username, final String password) {



        mApiClient.getAPI()
                .login(username, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                                           Response<ResponseBody> response) {


                        if (response.isSuccessful()) {

                            try {
                                if (response.body().string().contains("Login Successfully")) {
                                    mViewInterface.onLogin("Login Successfully");
                                } else {
                                    mViewInterface.onError("Username or Password is does not matched");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else errorHandle(response.code(), response.errorBody());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable e) {
                        Log.e("Error",call.request().toString());
                        if (e instanceof HttpException) {

                            int code = ((HttpException) e).response().code();

                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            mViewInterface.onError(APIErrors.get500ErrorMessage(responseBody));
                            errorHandle(code,responseBody);

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
