package com.example.root.pos.callbacks;

public interface UpdateView {

    public void onSuccess(String success,int code);
    public void onError(String error,int code);
}
