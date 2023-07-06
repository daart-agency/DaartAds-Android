package com.daartads.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdSize {

    public int width;
    public int height;
    public int flag;
    public String tag;

    @NonNull
    public static final AdSize RANDOM_SIZE;
    @NonNull
    public static final AdSize SIZE_720x480;
    @NonNull
    public static final AdSize SIZE_728x90;
    @NonNull
    public static final AdSize SIZE_480x320;
    @NonNull
    public static final AdSize SIZE_492x328;
    @NonNull
    public static final AdSize SIZE_468x60;
    @Nullable
    public static AdSize SIZE_360x240;

    @Nullable
    public static AdSize SIZE_320x100;

    @Nullable
    public static AdSize SIZE_320x50;

    @Nullable
    public static AdSize SIZE_300x250;

    @Nullable
    public static AdSize SIZE_295x98;

    @Nullable
    public static AdSize SIZE_160x600;


    static {
        RANDOM_SIZE = new AdSize(0, 0, 0, "randomSize"); // 0 --
        SIZE_720x480 = new AdSize(720, 480, 1, "720x480"); // 1 --
        SIZE_728x90 = new AdSize(728, 90, 2, "728x90"); // 2
        SIZE_480x320 = new AdSize(480, 320, 3, "480x320"); // 3
        SIZE_492x328 = new AdSize(492, 328, 4, "492x328"); // 4
        SIZE_468x60 = new AdSize(468, 60, 5, "468x60"); // 5 --
        SIZE_360x240 = new AdSize(360, 240, 6, "360x240");  // 6
        SIZE_320x100 = new AdSize(320, 100, 7, "320x100"); // 7 --
        SIZE_320x50 = new AdSize(320, 50, 8, "320x50"); // 8
        SIZE_300x250 = new AdSize(300, 250, 9, "300x250"); // 9 --
        SIZE_295x98 = new AdSize(295, 98, 10, "295x98"); // 10
        SIZE_160x600 = new AdSize(160, 600, 11, "160x600"); // 11
    }

    AdSize(int w, int h, int flag, String sizeTag) {
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
            this.flag = flag;
            this.tag = sizeTag;

//            Log.i("Soheil", "AdSize: w: " + w);
//            Log.i("Soheil", "AdSize: h: " + h);
        }
    }


}
