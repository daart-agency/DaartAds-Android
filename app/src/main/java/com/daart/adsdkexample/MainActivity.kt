package com.daart.adsdkexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daartads.sdk.AdSize
import com.daartads.sdk.DaartAds
import com.daartads.sdk.callback.AdListener
import com.daartads.sdk.model.BannerAd
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // replace with your token
        DaartAds.initialize("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE2Nzc2NzgzOTQsImlzcyI6ImRhYXJ0YWRzLmNvbSIsIm5iZiI6MTY3NzY3ODM5NCwiZXhwIjo0ODMxMjc4Mzk0LCJkYXRhIjp7InRva2VuIjoiJDJ5JDEwJFVWd3FIN012a2pjb29mVy9jemF2OGVoUFFkWXhmNGQyYi51OE1aRUs4SHhlQ2E0WTJaZ3kuIiwiZGEtaWQiOjc0fX0.54A-TV9_Oqs-hWsRVKpmSIWD8daHWG8e_fLRzsvaJGbzHiq0KzUlDA6ZHihWIlbR")

        val btnInterstitial = findViewById<Button>(R.id.btn_interstitial)

        val ads1 = findViewById<DaartAds>(R.id.testBanner)
        ads1.setAdSize(AdSize.BANNER)
        ads1.loadAd(object : AdListener {
            override fun onLoad(ad: BannerAd?) {
                Toast.makeText(this@MainActivity, "banner ad loaded success!", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onError(e: java.lang.Exception?) {
                Toast.makeText(this@MainActivity, "failed to load banner ad!", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        val ads2 = DaartAds(this)
        ads2.setAdSize(AdSize.INTERSTITIAL)
        ads2.loadAd(object : AdListener {
            override fun onError(e: Exception?) {
                Toast.makeText(
                    this@MainActivity,
                    "failed to load interstitial ad!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onLoad(ad: BannerAd?) {
                Toast.makeText(
                    this@MainActivity,
                    "interstitial ad loaded success!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        btnInterstitial.setOnClickListener { view: View? ->
            if (ads2.isLoaded) {
                ads2.show()
            } else {
                Toast.makeText(
                    this,
                    "Interstitial Ad not Load yet! please wait...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun fullBanner(view: View?) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "full")
        startActivity(intent)
    }

    fun largeBanner(view: View?) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "large")
        startActivity(intent)
    }

    fun leaderboardBanner(view: View?) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "leaderboard")
        startActivity(intent)
    }

    fun mediumRectangleBanner(view: View?) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "medium_rectangle")
        startActivity(intent)
    }

    fun wideSkyscraperBanner(view: View?) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "wide_skyscraper")
        startActivity(intent)
    }
}