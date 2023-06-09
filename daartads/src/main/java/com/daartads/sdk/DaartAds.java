package com.daartads.sdk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;

import com.daartads.sdk.callback.AdListener;
import com.daartads.sdk.config.Api;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class DaartAds extends RelativeLayout {

    private static String token;
    private boolean isLoaded;
    private Bitmap interstitialBitmap;

    //public Context mContext;
    public AdSize adSize;
    public String mUrl;

    public String mCid;
    public String mSource;

    public DaartAds(Context context) {
        super(context);
    }

    public DaartAds(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DaartAds(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Options, 0, 0);
//        String titleText = a.getString(R.styleable.Options_titleText);
//
//        @SuppressLint("ResourceAsColor")
//        int valueColor = a.getColor(R.styleable.Options_valueColor, android.R.color.holo_blue_light);
//        a.recycle();
    }

    public DaartAds(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static void initialize(String authToken) {
        token = authToken;
    }

    public void setAdSize(AdSize adSize) {
        this.adSize = adSize;


//        Log.i("Soheil", "setAdSize: w: " + adSize.width);
//        Log.i("Soheil", "setAdSize: h: " + adSize.height);

        if (adSize != AdSize.INTERSTITIAL) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.getLayoutParams();
            params.width = dpToPx(adSize.width);
            params.height = dpToPx(adSize.height);
            this.setLayoutParams(params);
        }
    }

    public void loadAd(AdListener listener) {
        Log.i("Soheil", "loadAd: super");

        if (listener == null) {
            listener.onError(new Exception("add listener is null..."));
        }

        HashMap<String, String> data = new HashMap<>();
        data.put("token", token);
//        data.put("adsize", getAdSize());

//        Log.i("Soheil", "adsize: "+ getAdSize());


        AdLoaderService loader = new AdLoaderService(data);
        loader.execute(Api.ADS_API);

//        setOnClickListener(view -> {
//            Log.i("Soheil", "onClick: main");
//            performCallback();
//        });
    }

//    private String getAdSize() {
//        String size;
//
//        if (adSize == AdSize.FULL_BANNER) {
//            size = "1";
//        } else if (adSize == AdSize.BANNER) {
//            size = "2";
//        } else if (adSize == AdSize.LARGE_BANNER) {
//            size = "3";
//        } else if (adSize == AdSize.LEADERBOARD) {
//            size = "4";
//        } else if (adSize == AdSize.MEDIUM_RECTANGLE) {
//            size = "5";
//        } else if (adSize == AdSize.WIDE_SKYSCRAPER) {
//            size = "6";
//        } else {
//            size = "10";
//        }
//
//        return size;
//    }

    public class AdLoaderService extends AsyncTask<String, String, String> {

//        interface Listener {
//            void onResult(String result);
//        }

        //        private Listener mListener;
        private final HashMap<String, String> mData;// post data

        public AdLoaderService(HashMap<String, String> data) {
            mData = data;
        }
//        public void setListener(Listener listener) {
//            mListener = listener;
//        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
        }

        @Override
        protected String doInBackground(String... params) {
            String str = "";

            try {

                HttpGet httpGet = new HttpGet(params[0]);
                HttpClient httpclient = new DefaultHttpClient();

                httpGet.addHeader("Authorization","Bearer " + token);

                HttpResponse response = httpclient.execute(httpGet);

//                StatusLine stat = response.getStatusLine();
//                int status = response.getStatusLine().getStatusCode();

                HttpEntity entity = response.getEntity();
                str = EntityUtils.toString(entity);

            } catch (IOException e) {
                e.printStackTrace();
                Log.i("Soheil", "doInBackground: " + e.getMessage());
            }




//            HttpClient client = new DefaultHttpClient();
//            HttpGet get = new HttpGet(params[0]);// in this case, params[0] is URL

//            try {
//
//                // set up post data
//                ArrayList<NameValuePair> nameValuePair = new ArrayList<>();
//                for (String key : mData.keySet()) {
//                    nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
//                }
//                get.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
//                HttpResponse response = client.execute(get);
//                StatusLine statusLine = response.getStatusLine();
//                if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
//                    result = EntityUtils.toByteArray(response.getEntity());
//                    str = new String(result, StandardCharsets.UTF_8);
//                }
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                Log.i("Soheil", "doInBackground: e1: " + e.getMessage());
//
//            } catch (Exception e) {
//                Log.i("Soheil", "doInBackground: e2: " + e.getMessage());
//            }


            return str;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("Soheil", "doInBackground, result: " + result);

            try {

                JSONObject mainObject = new JSONObject(result);

                JSONObject  headerObj = mainObject.getJSONObject("Header");
                int status = mainObject.getInt("Status");
                JSONObject  resultObj = mainObject.getJSONObject("Result");

                String builder = headerObj.getString("Builder");
                String uri = headerObj.getString("URI");

//                Log.i("Soheil", "onPostExecute, status: " + status);
//                Log.i("Soheil", "onPostExecute, uri: " + uri);
//                Log.i("Soheil", "onPostExecute, builder: " + builder);
                Log.i("Soheil", "onPostExecute ------------------------------------------------------------ ");

                if (status == 200) {
                    Log.i("Soheil", "onPostExecute, status: " + 200);

                    String imageUrl = "https://s2.uupload.ir/files/ic_launcher_j1o.png";
//                    String imageUrl = resultObj.getString("image_url");
                    String url = resultObj.getString("url");

                    Log.i("Soheil", "onPostExecute, imageUrl: " + imageUrl);
                    Log.i("Soheil", "onPostExecute, url: " + url);

                    if (AdSize.BANNER == DaartAds.this.adSize ||
                            AdSize.FULL_BANNER == DaartAds.this.adSize ||
                            AdSize.LARGE_BANNER.equals(adSize) ||
                            AdSize.LEADERBOARD.equals(adSize) ||
                            AdSize.MEDIUM_RECTANGLE.equals(adSize) ||
                            AdSize.WIDE_SKYSCRAPER.equals(adSize)) {

                        mUrl = url;

                        Log.i("Soheil", "AdType: <--BANNER-->");
                        showBanner(imageUrl);

                    } else if (AdSize.INTERSTITIAL.equals(adSize)) {
                        Log.i("Soheil", "AdType: <--INTERSTITIAL-->");
                        loadInterstitial(imageUrl);
                    }

                } else if (status == 401) {
                    Log.i("Soheil", "onPostExecute, status: " + 401);

                    String errorMsg = resultObj.getString("Error-Msg");
                    Log.i("Soheil", "onPostExecute, Error-Msg: " + errorMsg);
                }

            } catch (Exception e) {
                Log.i("Soheil", "onPostExecute: eeee: " + e.getMessage());
            }

//            // something...
//            if (mListener != null) {
//                mListener.onResult(result);
//            }
        }

    }

    private void showLoading() {
        ProgressBar pbLoading = new ProgressBar(this.getContext());
        pbLoading.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        pbLoading.setLayoutParams(params);
        params.setMargins(25, 25, 25, 25);
        addView(pbLoading);
    }

    public void showBanner(String image) {

        Log.i("Soheil", "showing banner...");

        AppCompatImageView imageBanner = new AppCompatImageView(this.getContext());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageBanner.setLayoutParams(params);
        removeAllViews();
        addView(imageBanner);

        imageBanner.setScaleType(ImageView.ScaleType.FIT_XY);

        Picasso.get()
                .load(image)
                .into(imageBanner, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i("Soheil", "picasso loaded!");
                        isLoaded = true;

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.i("Soheil", "picasso error!");
                        isLoaded = false;
                    }
                });

        imageBanner.setOnClickListener(v -> redirectAds());

    }

    private void loadInterstitial(String image) {
        Picasso.get().load(image).into(target);
    }

    private final Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            isLoaded = true;
            interstitialBitmap = bitmap;
            Log.i("Soheil", "onBitmapLoaded: ");
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            isLoaded = false;
            Log.i("Soheil", "onBitmapFailed: ");
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            isLoaded = false;
            Log.i("Soheil", "onPrepareLoad: ");
        }
    };

    public void show() {
        if (adSize == AdSize.INTERSTITIAL && interstitialBitmap != null) {
            showInterstitial();
        } else {
            Log.i("Soheil", "show: interstitialBitmap is null!");
        }
    }

    private void showInterstitial() {
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

        image.setImageBitmap(interstitialBitmap);
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private void redirectAds() {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mUrl));

        Log.i("Soheil", "redirectAds: " + mUrl);

        getContext().startActivity(i);
    }

    public boolean isLoaded() {
        return isLoaded;
    }
}
