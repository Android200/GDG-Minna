package com.gdg.gdgminna;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

public class WTMACTIVITY extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wtmactivity);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarwtm);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new WTM(), "About");
        adapter.addFragment(new WTMNews(), "News");
        adapter.addFragment(new WTMLeads(), "WTM Leads");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public Adapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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
            Intent a = new Intent(WTMACTIVITY.this,ABOUT.class);
            startActivity(a);
            return true;
        }else if (id == R.id.teams){
            Intent t = new Intent(WTMACTIVITY.this,TEAMS.class);
            startActivity(t);
            return true;
        }else if(id == R.id.website){
            Intent w = new Intent(getApplicationContext(),WEBSITE.class);
            startActivity(new Intent(w));
            return true;
        }else if(id == R.id.disclaimer){
            Intent d = new Intent(WTMACTIVITY.this,DISCLAIMER.class);
            startActivity(d);
            return true;
        }else if(id == R.id.location){
            Intent l =  new Intent(WTMACTIVITY.this,Location.class);
            startActivity(l);
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }






}
