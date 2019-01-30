package com.example.root.pos.callbacks;

import com.example.root.pos.model.UserData;

public interface ProfileView {

    public void onSuccess(UserData userData);
    public void onError(String error);


}
