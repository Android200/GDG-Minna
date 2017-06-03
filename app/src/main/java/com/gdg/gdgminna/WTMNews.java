package com.gdg.gdgminna;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class WTMNews extends Fragment {

    private WebView mwebView;
    ProgressBar loadingProgressBar;


    public WTMNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wtmnews, container, false);
    }

    public void onStart() {
        super.onStart();
        mwebView = (WebView) getView().findViewById(R.id.webViewwtnews);
        this.loadingProgressBar = (ProgressBar) getView().findViewById(R.id.progressbar_Horizontalwtmnews);
        //mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        mwebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mwebView.getSettings().setAppCachePath(getActivity().getCacheDir().getAbsolutePath());
        mwebView.getSettings().setAllowFileAccess(true);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.setFocusableInTouchMode(true);
        mwebView.setScrollbarFadingEnabled(true);
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        //mwebView.getSettings().setDomStorageEnabled(true);
        //mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        NetworkInfo nInfo = ((ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if(nInfo == null || !nInfo.isConnected()) {

            Snackbar.make(getView(), "Failed to load. Please Check your Internet Connection and Try Again!", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            loadingProgressBar.setVisibility(ProgressBar.INVISIBLE);
            //Load an Image as an error page
            //oops no internet connction
            mwebView.setVisibility(View.GONE);
            //View.setVisibility(ImageView.VISIBLE);
        }else{
            mwebView.loadUrl("https://plus.google.com/communities/100202454944694552166");
        }
        mwebView.setWebChromeClient(new WebChromeClient());
        mwebView.setWebViewClient(new WebViewClient());
        mwebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int newProgress){
                super.onProgressChanged(view,newProgress);
                WTMNews.this.loadingProgressBar.setProgress(newProgress);
                if(newProgress == 100) {
                    WTMNews.this.loadingProgressBar.setVisibility(getView().GONE);
                }else{
                    WTMNews.this.loadingProgressBar.setVisibility(getView().VISIBLE);
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





    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

}
