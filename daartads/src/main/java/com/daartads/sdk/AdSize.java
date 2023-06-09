package com.daartads.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdSize {

    public int width;
    public int height;
    public String tag;

    @NonNull
    public static final AdSize BANNER;
    @NonNull
    public static final AdSize FULL_BANNER;
    @NonNull
    public static final AdSize LARGE_BANNER;
    @NonNull
    public static final AdSize LEADERBOARD;
    @NonNull
    public static final AdSize MEDIUM_RECTANGLE;
    @NonNull
    public static final AdSize WIDE_SKYSCRAPER;
    @Nullable
    public static AdSize INTERSTITIAL;

    static {
        BANNER = new AdSize(320, 50, "320x50_mb");
        FULL_BANNER = new AdSize(468, 60, "468x60_as");
        LARGE_BANNER = new AdSize(320, 100, "320x100_as");
        LEADERBOARD = new AdSize(728, 90, "728x90_as");
        MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
        WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
        INTERSTITIAL = new AdSize(0, 0, "0x0_in");
    }

    AdSize(int w, int h, String sizeTag) {
        IllegalArgumentException exception;
        if (w < 0 && w != -1 && w != -3) {
            StringBuilder builder = new StringBuilder();
            builder.append("Invalid width for AdSize: ");
            builder.append(w);
            exception = new IllegalArgumentException(builder.toString());
            throw exception;
        } else if (h < 0 && h != -2 && h != -4) {
            StringBuilder builder = new StringBuilder();
            builder.append("Invalid height for AdSize: ");
            builder.append(h);
            exception = new IllegalArgumentException(builder.toString());
            throw exception;

        } else {
            this.width = w;
            this.height = h;
            this.tag = sizeTag;

//            Log.i("Soheil", "AdSize: w: " + w);
//            Log.i("Soheil", "AdSize: h: " + h);
        }
    }


}
