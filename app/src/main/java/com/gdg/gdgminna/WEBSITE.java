package com.gdg.gdgminna;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WEBSITE extends AppCompatActivity {
    private WebView mwebView;
    ProgressBar loadingProgressBar;
    CoordinatorLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mwebView = (WebView) findViewById(R.id.webViewwebsite);
        this.loadingProgressBar = (ProgressBar) findViewById(R.id.progressbar_website);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        mwebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mwebView.getSettings().setAppCachePath(getCacheDir().getAbsolutePath());
        mwebView.getSettings().setAllowFileAccess(true);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.setFocusableInTouchMode(true);
        mwebView.setScrollbarFadingEnabled(true);
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        mwebView.getSettings().setDomStorageEnabled(true);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        NetworkInfo nInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (nInfo == null || !nInfo.isConnected()) {

            Toast.makeText(WEBSITE.this, "Failed to load. Please Check your Internet Connection and Try Again!", Toast.LENGTH_LONG).show();

            loadingProgressBar.setVisibility(ProgressBar.INVISIBLE);
            //Load an Image as an error page
            //oops no internet connction
            mwebView.setVisibility(View.GONE);
            //View.setVisibility(ImageView.VISIBLE);
        } else {
            mwebView.loadUrl("http://minna.gdg.ng/");
        }


        mwebView.setWebChromeClient(new WebChromeClient());
        mwebView.setWebViewClient(new WebViewClient());
        mwebView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                WEBSITE.this.loadingProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    WEBSITE.this.loadingProgressBar.setVisibility(View.GONE);
                } else {
                    WEBSITE.this.loadingProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        mwebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mwebView.canGoBack()) {
                    mwebView.goBack();
                    return true;
                }
                return false;
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id=item.getItemId();
        if( id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
