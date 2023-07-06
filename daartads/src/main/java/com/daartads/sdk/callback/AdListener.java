package com.daartads.sdk.callback;

public interface AdListener {
    void notExist();
    void onLoad();
    void onError(Exception e);
}
