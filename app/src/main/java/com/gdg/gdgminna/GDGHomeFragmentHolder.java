package com.gdg.gdgminna;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableFuture;


/**
 * A simple {@link Fragment} subclass.
 */
public class GDGHomeFragmentHolder extends Fragment {
    View rootView;
    public static ViewPager viewPager;
    public static TabLayout tabs;
    public static int int_items=3;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_gdghome_fragment_holder,null);
        // Setting ViewPager for each Tabs


        // Set Tabs inside Toolbar
        tabs = (TabLayout) rootView.findViewById(R.id.tabsmain);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpagermain);


        viewPager.setAdapter(new myAdapter(getChildFragmentManager()));
        tabs.post(new Runnable() {
            @Override
            public void run() {
                tabs.setupWithViewPager(viewPager);
            }
        });
        return rootView;
    }
    class myAdapter extends FragmentPagerAdapter{
        public myAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int positon){
            switch (positon){
                case 0: return new GDGHOME();
                case 1: return new PPAGE();
                case 2: return new BLOG();
            }
            return null;
        }
        @Override
        public int getCount(){
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0: return "Info";
                case 1: return "News";
                case 2: return "Blog";
            }
            return null;
        }
    }
}
