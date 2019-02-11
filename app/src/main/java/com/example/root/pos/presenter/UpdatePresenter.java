package com.example.root.pos.presenter;

import com.example.root.pos.callbacks.UpdateView;
import com.example.root.pos.errors.APIErrors;
import com.example.root.pos.model.UserData;
import com.example.root.pos.services.APIClient;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Optional;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class UpdatePresenter {

    private UpdateView mViewInterface;
    private APIClient mApiClient;

    public UpdatePresenter(UpdateView view) {
        this.mViewInterface = view;

        if (this.mApiClient == null) {
            this.mApiClient = new APIClient();
        }
    }

    public void update(String name, String username,
                        String password, String confirmpassword,
                        String address,String occupationnname, String phonenumber,
                        int id) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("confirmpassword", confirmpassword);
        jsonObject.addProperty("address", address);
        jsonObject.addProperty("occupationnname", occupationnname);
        jsonObject.addProperty("phonenumber", phonenumber);




        mApiClient.getAPI()
                .UpdateUser(id,jsonObject)
                .enqueue(new Callback<Optional<UserData>>() {
                    @Override
                    public void onResponse(Call<Optional<UserData>> call, Response<Optional<UserData>> response) {


                        if (response.isSuccessful()) {
                           Optional<UserData>  UserData = response.body();
                            if (UserData != null) {
                                mViewInterface.onSuccess("Updated",response.code());
                            } else {
                                mViewInterface.onError("Error fetching data",response.code());
                            }
                        } else errorHandle(response.code(), response.errorBody());


                    }

                    @Override
                    public void onFailure(Call<Optional<UserData>> call, Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            int code = ((HttpException) e).response().code();
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            errorHandle(code, responseBody);

                        } else if (e instanceof SocketTimeoutException) {

                            mViewInterface.onError("Server connection error",500);
                        } else if (e instanceof IOException) {
                            mViewInterface.onError("IOException",500);
                        } else {
                            mViewInterface.onError("Unknown exception",500);
                        }
                    }
                });
    }

    private void errorHandle(int code, ResponseBody responseBody){
        if (code == 500) mViewInterface.onError(APIErrors.get500ErrorMessage(responseBody),500);
        else if(code == 406){
            try {
                JSONObject jObjError = new JSONObject(responseBody.string());
                mViewInterface.onError(jObjError.getString("message"),400);
            } catch (Exception e) {
                mViewInterface.onError(e.getMessage(),400);
            }
        }
        else mViewInterface.onError(APIErrors.getErrorMessage(responseBody),500);
    }


}
