package com.gdg.gdgminna;

import android.app.PendingIntent;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, new GDGHomeFragmentHolder()).commit();

        fab=(FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://docs.google.com/forms/d/e/1FAIpQLSfJvVI3SviMIUhXR2X-xZHd-7fXRpeT9Ym38wTl_Lf989QNYg/viewform");
            }
        });
    }

    public void OpeninCustomTab(String url){
        Uri website;
        if(!url.contains("https://") && !url.contains("http://")){
            website = Uri.parse("http://"+url);
        }else{
            website = Uri.parse(url);
        }

        CustomTabsIntent.Builder customtabIntent = new CustomTabsIntent.Builder();
        customtabIntent.setToolbarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        customtabIntent.setShowTitle(true);
        customtabIntent.addDefaultShareMenuItem();
        customtabIntent.setStartAnimations(this,R.anim.right_in, R.anim.left_out);
        customtabIntent.setExitAnimations(this,R.anim.left_in,R.anim.right_out);
        Intent copyIntent = new Intent(this,CopyURLBroadcast.class);
        PendingIntent copypendingIntent = PendingIntent.getBroadcast(this,0, copyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        customtabIntent.addMenuItem("Copy Link",copypendingIntent);

        if(chromeInstalled()){
            customtabIntent.build().intent.setPackage("com.android.chrome");
        }
        customtabIntent.build().launchUrl(this,website);
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.rate) {
            String url="https://play.google.com/store/apps/details?id=com.gdg.gdgminna";
            Intent r= new Intent(Intent.ACTION_VIEW);
            r.setData(Uri.parse(url));
            startActivity(r);
            return true;
        }else if(id == R.id.share){
            Intent share = new Intent (Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_SUBJECT,"GDG Minna");
            share.putExtra(Intent.EXTRA_TEXT,"Download GDG Minna App to know more about Google Developers Group Minna Community\nhttps://play.google.com/store/apps/details?id=com.gdg.gdgminna");
            startActivity(share);
            return true;
        }else if(id == R.id.about){
            Intent a = new Intent(MainActivity.this,ABOUT.class);
            startActivity(a);
            return true;
        }else if (id == R.id.teams){
            Intent t = new Intent(MainActivity.this,TEAMS.class);
            startActivity(t);
            return true;
        }else if(id == R.id.website){
            Intent w = new Intent(getApplicationContext(),WEBSITE.class);
            startActivity(new Intent(w));
            return true;
        }else if(id == R.id.disclaimer){
            Intent d = new Intent(MainActivity.this,DISCLAIMER.class);
            startActivity(d);
            return true;
        }else if(id == R.id.location){
            Intent l =  new Intent(MainActivity.this,Location.class);
            startActivity(l);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.gdghome) {
            fragment = new GDGHomeFragmentHolder();
            ft.replace(R.id.content, fragment).commit();
        }if (id == R.id.wtminna) {
            Intent wtm = new Intent(MainActivity.this,WTMACTIVITY.class);
            startActivity(wtm);
        }else if(id == R.id.event){
            fragment = new EVENTS();
            ft.replace(R.id.content, fragment).commit();
        }   else if (id == R.id.organizers) {
            fragment = new ORGANIZERS();
            ft.replace(R.id.content, fragment).commit();
        } else if (id == R.id.contactus) {
            fragment = new CONTACT_US();
            ft.replace(R.id.content, fragment).commit();
        }else if (id == R.id.sponsors) {
            fragment = new SPONSORS();
            ft.replace(R.id.content, fragment).commit();
        }else if (id == R.id.register) {
            fragment = new REGISTER();
            ft.replace(R.id.content, fragment).commit();
        }else if (id == R.id.forum) {
            fragment = new FORUM();
            ft.replace(R.id.content, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
