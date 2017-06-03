package com.gdg.gdgminna;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class PPAGE extends Fragment {
    public WebView mwebView;
    private View rootView = null;
    ProgressBar loadingProgressBar;
    ImageView View;
    //Button btnreload;
    //ProgressBar loadingTitle;

    public PPAGE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ppage, container, false);


    }

    public void onStart() {
        super.onStart();
        mwebView = (WebView) getView().findViewById(R.id.webViewpluspage);
        //this.btnreload = (Button)getView().findViewById(R.id.btnreloadpp);
        this.loadingProgressBar = (ProgressBar) getView().findViewById(R.id.progressbar_PlusPage);
        View = (ImageView) getView().findViewById(R.id.mx);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        mwebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mwebView.getSettings().setAppCachePath(getActivity().getCacheDir().getAbsolutePath());
        mwebView.getSettings().setAllowFileAccess(true);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.setFocusableInTouchMode(true);
        mwebView.setScrollbarFadingEnabled(true);
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        mwebView.getSettings().setDomStorageEnabled(true);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        NetworkInfo nInfo = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (nInfo == null || !nInfo.isConnected()){

            //Snackbar.make(getView(), "Failed to load. Please Check your Internet Connection and Try Again!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Toast.makeText(getActivity(),"Failed to Load. Please Check your Internet Connection and Try Again!",Toast.LENGTH_SHORT).show();

            loadingProgressBar.setVisibility(ProgressBar.INVISIBLE);
            //Load an Image as an error page
            //oops no internet connction
            mwebView.setVisibility(View.GONE);
            //View.setVisibility(ImageView.VISIBLE);
            //btnreload.setVisibility(View.VISIBLE);

           /** btnreload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PPAGE.this.mwebView.loadUrl("https://plus.google.com/b/113224920428716041640/+GdgminnaBlogspot/posts");

                }
            });**/
        } else {
            mwebView.loadUrl("https://plus.google.com/b/113224920428716041640/+GdgminnaBlogspot/posts");
        }
        mwebView.setWebChromeClient(new WebChromeClient());
        mwebView.setWebViewClient(new WebViewClient());
        mwebView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                PPAGE.this.loadingProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    PPAGE.this.loadingProgressBar.setVisibility(getView().GONE);
                } else {
                    PPAGE.this.loadingProgressBar.setVisibility(getView().VISIBLE);
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
}
