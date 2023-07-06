package com.daart.adsdkexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daartads.sdk.AdSize
import com.daartads.sdk.DaartAds
import com.daartads.sdk.callback.AdListener

class TestBannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_banner)

        val adSize = intent.getStringExtra("adSize")

        val adView = findViewById<DaartAds>(R.id.ad_view)

        if (adSize == "random_size") {
            adView.setAdSize(this, AdSize.RANDOM_SIZE)

        } else if (adSize == "720_480") {
            adView.setAdSize(this, AdSize.SIZE_720x480)

        } else if (adSize == "728_90") {
            adView.setAdSize(this, AdSize.SIZE_728x90)

        } else if (adSize == "480_320") {
            adView.setAdSize(this, AdSize.SIZE_480x320)

        } else if (adSize == "492_328") {
            adView.setAdSize(this, AdSize.SIZE_492x328)

        } else if (adSize == "468_60") {
            adView.setAdSize(this, AdSize.SIZE_468x60)

        } else if (adSize == "360_240") {
            adView.setAdSize(this, AdSize.SIZE_360x240)

        } else if (adSize == "320_100") {
            adView.setAdSize(this, AdSize.SIZE_320x100)

        } else if (adSize == "320_50") {
            adView.setAdSize(this, AdSize.SIZE_320x50)

        } else if (adSize == "300_250") {
            adView.setAdSize(this, AdSize.SIZE_300x250)

        } else if (adSize == "295_98") {
            adView.setAdSize(this, AdSize.SIZE_295x98)

        } else if (adSize == "160_600") {
            adView.setAdSize(this, AdSize.SIZE_160x600)
        }

        adView.loadAd(object : AdListener {
            override fun notExist() {
                Toast.makeText(
                    this@TestBannerActivity,
                    "There are no ads to show!",
                    Toast.LENGTH_SHORT
                ).show()

                adView.visibility = View.GONE
            }

            override fun onLoad() {
                Toast.makeText(
                    this@TestBannerActivity,
                    "ad loaded success!",
                    Toast.LENGTH_SHORT
                ).show()
                adView.visibility = View.VISIBLE
            }

            override fun onError(e: java.lang.Exception?) {
                Toast.makeText(
                    this@TestBannerActivity,
                    "failed to load ad!",
                    Toast.LENGTH_SHORT
                ).show()
                adView.visibility = View.GONE
            }
        })
    }
}