package com.daart.adsdkexample

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daartads.sdk.AdSize
import com.daartads.sdk.DaartAds
import com.daartads.sdk.callback.AdListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // replace with your token
        DaartAds.initialize(this, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE2Nzc2NzgzOTQsImlzcyI6ImRhYXJ0YWRzLmNvbSIsIm5iZiI6MTY3NzY3ODM5NCwiZXhwIjo0ODMxMjc4Mzk0LCJkYXRhIjp7InRva2VuIjoiJDJ5JDEwJFVWd3FIN012a2pjb29mVy9jemF2OGVoUFFkWXhmNGQyYi51OE1aRUs4SHhlQ2E0WTJaZ3kuIiwiZGEtaWQiOjc0fX0.54A-TV9_Oqs-hWsRVKpmSIWD8daHWG8e_fLRzsvaJGbzHiq0KzUlDA6ZHihWIlbR")

        val btn720_480 = findViewById<Button>(R.id.btn_720_480)

        // interstitial ad
        btn720_480.setOnClickListener {

            val pd = ProgressDialog(this@MainActivity)
            pd.setTitle("Loading...")
            pd.setMessage("Interstitial ad")
            pd.setCancelable(true)
            pd.show()

            val interstitialAd = DaartAds(this)
            interstitialAd.setAdSize(this, AdSize.SIZE_720x480)
            interstitialAd.loadAd(object : AdListener {
                override fun onError(e: Exception?) {
                    pd.dismiss()

                    Toast.makeText(
                        this@MainActivity,
                        "failed to load interstitial ad!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun notExist() {
                    Toast.makeText(
                        this@MainActivity,
                        "There are no ads to show!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onLoad() {
                    pd.dismiss()
                }
            })

        }
    }

    fun load_random_size(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "random_size")
        startActivity(intent)
    }

    fun load_720_480(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "720_480")
        startActivity(intent)
    }

    fun load_728_90(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "728_90")
        startActivity(intent)
    }

    fun load_480_320(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "480_320")
        startActivity(intent)
    }

    fun load_492_328(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "492_328")
        startActivity(intent)
    }

    fun load_468_60(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "468_60")
        startActivity(intent)
    }

    fun load_360_240(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "360_240")
        startActivity(intent)
    }

    fun load_320_100(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "320_100")
        startActivity(intent)
    }

    fun load_320_50(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "320_50")
        startActivity(intent)
    }

    fun load_300_250(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "300_250")
        startActivity(intent)
    }

    fun load_295_98(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "295_98")
        startActivity(intent)
    }

    fun load_160_600(v: View) {
        val intent = Intent(baseContext, TestBannerActivity::class.java)
        intent.putExtra("adSize", "160_600")
        startActivity(intent)
    }
}