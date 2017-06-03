package com.gdg.gdgminna;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Isaac extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isaac);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call=new Intent(Intent.ACTION_CALL, Uri.parse("tel:+2347031162515"));
                try{
                    startActivity(call);
                } catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(), "Activity not founded", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    public void isaacplus(View view){
        OpeninCustomTab("https://plus.google.com/+IsaacMarcusz");
    }

    public void isaacfacebook(View v){
        OpeninCustomTab("https://www.facebook.com/isaac.marcus.12");
    }
    public void isaactwitter(View v){
        OpeninCustomTab("https://twitter.com/IsaacMarcusz");
    }

    public void isaacinstagram(View v){
        OpeninCustomTab("https://instagram.com/isaac.marcus/");
    }

    public void isaaclinkdn(View v){
        OpeninCustomTab("http://ng.linkedin.com/pub/isaac-marcus/aa/65b/b87/en");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
