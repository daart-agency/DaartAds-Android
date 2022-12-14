# DaartAds Android
[![](https://jitpack.io/v/daart-agency/DaartAds-Android.svg)](https://jitpack.io/#daart-agency/DaartAds-Android)
This library allows you to access the DaartAds API in your application.

## Installation

#### Include the following lines in your root ```build.gradle```
```java
    allprojects {
        repositories {
          maven { url 'https://jitpack.io' }
        }
    }
```

#### Include the dependencies listed below in your ```app/build.gradle```
```java
    dependencies {
        implementation 'com.github.daart-agency:DaartAds-Android:alpha'
    }
```

## Usage
```java
DaartAds.initialize("PLACE_YOUR_TOKEN");
```

### For Displaying Ad Banner
```java
DaartAds banner = findViewById(R.id.testBanner);
banner.setAdSize(AdSize.BANNER);

banner.loadAd(new AdListener() {
   @Override
   public void onLoad(com.daartads.sdk.model.BannerAd ad) {
       Toast.makeText(MainActivity.this, "banner ad loaded success!", Toast.LENGTH_SHORT).show();
   }

   @Override
   public void onError(Exception e) {
       Toast.makeText(MainActivity.this, "failed to load banner ad!", Toast.LENGTH_SHORT).show();
   }
});
```

### interstitial ad
```java
DaartAds interstitial = new DaartAds(this);
interstitial.setAdSize(AdSize.INTERSTITIAL);
interstitial.loadAd(new AdListener() {
    @Override
    public void onLoad(com.daartads.sdk.model.BannerAd ad) {
        Toast.makeText(MainActivity.this, "interstitial ad loaded success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(MainActivity.this, "failed to load interstitial ad!", Toast.LENGTH_SHORT).show();
    }
});
```

## Ad Sizes

| Type  | Size |
| ----- | ---- |
| BANNER | 320 x 50 |
| FULL_BANNER  | 320 x 100 |
| LARGE_BANNER  | 728 x 90 |
| LEADERBOARD  | 300 x 250 |
| MEDIUM_RECTANGLE | 160 x 600 |
| INTERSTITIAL | Full Screen |

