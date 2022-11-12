package com.daartads.sdk.callback;

import com.daartads.sdk.model.BannerAd;

public interface AdListener {
    void onLoad(BannerAd ad);
    void onError(Exception e);
}
