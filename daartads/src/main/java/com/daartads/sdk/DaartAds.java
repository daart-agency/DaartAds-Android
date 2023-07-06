package com.daartads.sdk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daartads.sdk.callback.AdListener;
import com.daartads.sdk.config.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DaartAds extends RelativeLayout {

    private static String mToken;
    public static Context mContext;
    private AdListener mListener;
    private ProgressBar pbLoading;
    private RelativeLayout.LayoutParams mParams;

    public AdSize adSize;
    public String mUrl;

    public DaartAds(Context mContext) {
        super(mContext);
    }

    public DaartAds(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
    }

    public DaartAds(Context mContext, AttributeSet attrs, int defStyleAttr) {
        super(mContext, attrs, defStyleAttr);
    }

    public DaartAds(Context mContext, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(mContext, attrs, defStyleAttr, defStyleRes);
    }

    public static void initialize(Context c, String authToken) {
        mContext = c;
        mToken = authToken;
    }

    public void setAdSize(Context c, AdSize adSize) {
        this.adSize = adSize;

        mParams = new RelativeLayout.LayoutParams(dpToPx(adSize.width), dpToPx(adSize.height));
        mParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        this.setLayoutParams(mParams);
    }

    public void loadAd(AdListener listener) {
        if (listener == null) {
            listener.onError(new Exception("add listener is null..."));
        } else {
            mListener = listener;
        }
        getAd();
    }

    private void getAd() {

        showLoading();

        RequestQueue queue = Volley.newRequestQueue(mContext);

        StringRequest myReq = new StringRequest(Request.Method.GET, Api.ADS_API + adSize.flag,
                response -> {

                    try {
                        JSONObject mainObject = new JSONObject(response);

                        // Header object
                        JSONObject  headerObj = mainObject.getJSONObject("Header");
                        int status = mainObject.getInt("Status");

                        // Result object
                        JSONObject resultObj = null;

                        try {
                            resultObj = mainObject.getJSONObject("Result");
                        } catch (Exception ignored) {
                        }

                        String builder = headerObj.getString("Builder");
                        String uri = headerObj.getString("URI");

                        if (resultObj == null) {
                            mListener.notExist();
                            hideLoading();

                        } else {

                            if (status == 200) {
                                String imageUrl = resultObj.getString("image_url");
                                String url = resultObj.getString("url");

                                mUrl = resultObj.getString("url");

                                // show in interstitial ad view
                                if (AdSize.SIZE_720x480.equals(adSize)) {
                                    loadInterstitialImage(imageUrl);

                                    // show in banner ad view
                                } else {
                                    mListener.onLoad();
                                    showBanner(imageUrl);
                                }

                            }   else if (status == 401) {
                                String errorMsg = resultObj.getString("Error-Msg");
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ,
                error -> {

                }
        )
        {
            protected Map<String, String> getParams() {
                return new HashMap<>();
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params1 = new HashMap<>();
                params1.put("Authorization", "Bearer " + mToken);
                return params1;
            }
        };

        queue.add(myReq);
    }

    private void showLoading() {
        pbLoading = new ProgressBar(this.getContext());
        pbLoading.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        pbLoading.setLayoutParams(params);
        params.setMargins(5, 5, 5, 5);
        addView(pbLoading);
    }

    private void hideLoading() {
        if (pbLoading != null) {
            pbLoading.setVisibility(INVISIBLE);
        }
    }

    public void showBanner(String image) {

        ImageView imageBanner = new ImageView (this.getContext());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dpToPx(adSize.width), dpToPx(adSize.height));
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins(50,0,50,0);

        imageBanner.setBackgroundColor(Color.YELLOW);
        imageBanner.setLayoutParams(params);
        imageBanner.setScaleType(ImageView.ScaleType.FIT_XY);
        removeAllViews();
        addView(imageBanner);

        Glide.with(this).load(image).into(imageBanner);

        imageBanner.setOnClickListener(v -> redirectAds());

        imageBanner.setBackgroundColor(Color.GRAY);
    }

    private void loadInterstitialImage(String url) {
        mListener.onLoad();

        Dialog dialog = new Dialog(this.getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.view_interstitial_ad);
        dialog.show();

        ImageButton btnClose = dialog.findViewById(R.id.btn_close);
        ImageView image = dialog.findViewById(R.id.image);
        Button btnOpen = dialog.findViewById(R.id.btn_open);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnClose.setOnClickListener(view -> dialog.dismiss());
        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnOpen.setOnClickListener(view -> {
            redirectAds();
            dialog.dismiss();
        });

        Glide.with(this).load(url).into(image);
    }

    private void redirectAds() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mUrl));

        getContext().startActivity(i);
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public float convertPxToDp(Context context, int px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

}
