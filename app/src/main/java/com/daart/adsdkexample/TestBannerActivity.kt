package com.daart.adsdkexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daartads.sdk.AdSize
import com.daartads.sdk.DaartAds
import com.daartads.sdk.callback.AdListener
import com.daartads.sdk.model.BannerAd

class TestBannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_banner)
        val adSize = intent.getStringExtra("adSize")
        val banner = findViewById<DaartAds>(R.id.testBanner)
        if (adSize == "full") {
            banner.setAdSize(AdSize.FULL_BANNER)
        } else if (adSize == "large") {
            banner.setAdSize(AdSize.LARGE_BANNER)
        } else if (adSize == "leaderboard") {
            banner.setAdSize(AdSize.LEADERBOARD)
        } else if (adSize == "medium_rectangle") {
            banner.setAdSize(AdSize.MEDIUM_RECTANGLE)
        } else if (adSize == "wide_skyscraper") {
            banner.setAdSize(AdSize.WIDE_SKYSCRAPER)
        }
        banner.loadAd(object : AdListener {
            override fun onLoad(ad: BannerAd?) {
                Toast.makeText(
                    this@TestBannerActivity,
                    "banner ad loaded success!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onError(e: java.lang.Exception?) {
                Toast.makeText(
                    this@TestBannerActivity,
                    "failed to load banner ad!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}