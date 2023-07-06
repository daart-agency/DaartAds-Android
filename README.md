# Daart Android Advertisement SDK (DaartAds)

#### Add bellow lines in your root build.gradle
```sh
    allprojects {
        repositories {
          maven { url 'https://jitpack.io' }
        }
    }
```

#### Add the dependency and app/build.gradle
```sh
    dependencies {
        implementation 'com.github.soheilazimi2017:daartads-android:1.2'
    }
```
## How to implement

## Initialize SDK
```sh
DaartAds.initialize("PLACE_YOUR_AUTH_TOKEN");
```

## Show banner ad
```sh
val bannerAd = findViewById<DaartAds>(R.id.testBanner)
        bannerAd.setAdSize(AdSize.BANNER)
        bannerAd.loadAd(object : AdListener {
            override fun onLoad(ad: BannerAd?) {
                Toast.makeText(this@MainActivity, "banner ad loaded success!", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onError(e: java.lang.Exception?) {
                Toast.makeText(this@MainActivity, "failed to load banner ad!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
```

## Show interstitial ad
```sh
val interstitialAd = DaartAds(this)
        interstitialAd.setAdSize(AdSize.INTERSTITIAL)
        interstitialAd.loadAd(object : AdListener {
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
```

## Ad sizes

| Type  | Size |
| ------------- | ------------ |
| BANNER  | 320 x 50 |
| FULL_BANNER  | 320 x 100 |
| LARGE_BANNER  | 728 x 90 |
| LEADERBOARD  | 300 x 250 |
| MEDIUM_RECTANGLE  | 160 x 600 |
| INTERSTITIAL  | Full Screen |

