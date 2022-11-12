package com.daart.adsdkexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daartads.sdk.AdSize;
import com.daartads.sdk.DaartAds;
import com.daartads.sdk.callback.AdListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInterstitial = findViewById(R.id.btn_interstitial);

        DaartAds.initialize("$2y$10$UVwqH7MvkjcoofW/czav8ehPQdYxf4d2b.u8MZEK8HxeCa4Y2Zgy.");

        DaartAds ads1 = findViewById(R.id.testBanner);
        ads1.setAdSize(AdSize.BANNER);
        ads1.loadAd(new AdListener() {
            @Override
            public void onLoad(com.daartads.sdk.model.BannerAd ad) {
                Toast.makeText(MainActivity.this, "banner ad loaded success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MainActivity.this, "failed to load banner ad!", Toast.LENGTH_SHORT).show();
            }
        });

        DaartAds ads2 = new DaartAds(this);
        ads2.setAdSize(AdSize.INTERSTITIAL);
        ads2.loadAd(new AdListener() {
            @Override
            public void onLoad(com.daartads.sdk.model.BannerAd ad) {
                Toast.makeText(MainActivity.this, "interstitial ad loaded success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MainActivity.this, "failed to load interstitial ad!", Toast.LENGTH_SHORT).show();
            }
        });

        btnInterstitial.setOnClickListener(view -> {
            if (ads2.isLoaded()) {
                ads2.show();
            } else {
                Toast.makeText(this, "Interstitial Ad not Load yet! please wait...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fullBanner(View view) {
        Intent intent = new Intent(getBaseContext(), TestBannerActivity.class);
        intent.putExtra("adSize", "full");
        startActivity(intent);
    }

    public void largeBanner(View view) {
        Intent intent = new Intent(getBaseContext(), TestBannerActivity.class);
        intent.putExtra("adSize", "large");
        startActivity(intent);
    }

    public void leaderboardBanner(View view) {
        Intent intent = new Intent(getBaseContext(), TestBannerActivity.class);
        intent.putExtra("adSize", "leaderboard");
        startActivity(intent);
    }

    public void mediumRectangleBanner(View view) {
        Intent intent = new Intent(getBaseContext(), TestBannerActivity.class);
        intent.putExtra("adSize", "medium_rectangle");
        startActivity(intent);
    }

    public void wideSkyscraperBanner(View view) {
        Intent intent = new Intent(getBaseContext(), TestBannerActivity.class);
        intent.putExtra("adSize", "wide_skyscraper");
        startActivity(intent);
    }
}