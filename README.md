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
        implementation 'com.github.soheilazimi2017:DaartAds:alpha'
    }
```
## How to implement

## initialize SDK
```sh
DaartAds.initialize("PLACE_YOUR_TOKEN");
```

## Show banner ad
```sh
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

## Show interstitial ad
```sh
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

## Ad sizes
```sh
    BANNER
    FULL_BANNER
    LARGE_BANNER
    LEADERBOARD
    MEDIUM_RECTANGLE
    WIDE_SKYSCRAPER
    INTERSTITIAL
```

