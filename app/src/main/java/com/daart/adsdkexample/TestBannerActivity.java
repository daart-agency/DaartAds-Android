package com.daart.adsdkexample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daartads.sdk.AdSize;
import com.daartads.sdk.DaartAds;
import com.daartads.sdk.callback.AdListener;

public class TestBannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_banner);

        String adSize = getIntent().getStringExtra("adSize");

        DaartAds banner = findViewById(R.id.testBanner);

        if (adSize.equals("full")) {
            banner.setAdSize(AdSize.FULL_BANNER);
        } else if (adSize.equals("large")) {
            banner.setAdSize(AdSize.LARGE_BANNER);
        } else if (adSize.equals("leaderboard")) {
            banner.setAdSize(AdSize.LEADERBOARD);
        } else if (adSize.equals("medium_rectangle")) {
            banner.setAdSize(AdSize.MEDIUM_RECTANGLE);
        } else if (adSize.equals("wide_skyscraper")) {
            banner.setAdSize(AdSize.WIDE_SKYSCRAPER);
        }

        banner.loadAd(new AdListener() {
            @Override
            public void onLoad(com.daartads.sdk.model.BannerAd ad) {
                Toast.makeText(TestBannerActivity.this, "banner ad loaded success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(TestBannerActivity.this, "failed to load banner ad!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}