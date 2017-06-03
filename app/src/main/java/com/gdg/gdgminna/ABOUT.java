package com.gdg.gdgminna;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class ABOUT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void androidstudio(View view){
        OpeninCustomTab("https://developer.android.com/studio/intro/index.html");
        //String url = "https://developer.android.com/studio/intro/index.html";
        //Intent i = new Intent(Intent.ACTION_VIEW);
        //i.setData(Uri.parse(url));
        //startActivity(i);
    }
    public void android(View view){
        OpeninCustomTab("https://developer.android.com/index.html");
    }
    public void playstore(View view){
        OpeninCustomTab("https://play.google.com/apps");
    }
    public void chrome(View view){
        OpeninCustomTab("https://developer.chrome.com/extensions/api_index");
    }
    public void calendar(View view){
        OpeninCustomTab("https://developers.google.com/google-apps/calendar/");
    }
    public void maps(View view){
        OpeninCustomTab("https://developers.google.com/maps/");
    }
    public void youtube(View view){
        OpeninCustomTab("https://developers.google.com/youtube/");
    }
    public void uiux(View view){
        OpeninCustomTab("https://design.google.com");
    }

    public void OpeninCustomTab(String url) {
        Uri website;
        if (!url.contains("https://") && !url.contains("http://")) {
            website = Uri.parse("http://" + url);
        } else {
            website = Uri.parse(url);
        }
        CustomTabsIntent.Builder customtabIntent = new CustomTabsIntent.Builder();
        customtabIntent.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        customtabIntent.setShowTitle(true);
        customtabIntent.addDefaultShareMenuItem();
        customtabIntent.setStartAnimations(this,R.anim.right_in, R.anim.left_out);
        customtabIntent.setExitAnimations(this,R.anim.left_in,R.anim.right_out);
        Intent copyIntent = new Intent(this, CopyURLBroadcast.class);
        PendingIntent copypendingIntent = PendingIntent.getBroadcast(this,0, copyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        customtabIntent.addMenuItem("Copy Link", copypendingIntent);

        if (chromeInstalled()) {
            customtabIntent.build().intent.setPackage("com.android.chrome");
        }
        customtabIntent.build().launchUrl(this, website);
    }

    private boolean chromeInstalled(){
        try{
            getPackageManager().getPackageInfo("com.android.chrome",0);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
