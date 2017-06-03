package com.gdg.gdgminna;


import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class EVENTS extends Fragment {
    private View rootView;


    public EVENTS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_event, container, false);
        CardView iwd = (CardView)rootView.findViewById(R.id.iwd);
        CardView gcpnext = (CardView)rootView.findViewById(R.id.gcpnext);
        CardView studyjam = (CardView)rootView.findViewById(R.id.studyjam);
        CardView ioextended = (CardView)rootView.findViewById(R.id.ioextended);
        CardView devfest = (CardView)rootView.findViewById(R.id.devfest);

        iwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://plus.google.com/communities/100202454944694552166");
            }
        });

        gcpnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://cloudnext.withgoogle.com/");
            }
        });

        studyjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("http://developerstudyjams.com/");
            }
        });

        ioextended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://events.google.com/io2016/extended");
            }
        });

        devfest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("http://ncdevfest.com/");
            }
        });



        return rootView;
    }

    public void OpeninCustomTab(String url) {
        Uri website;
        if (!url.contains("https://") && !url.contains("http://")) {
            website = Uri.parse("http://" + url);
        } else {
            website = Uri.parse(url);
        }
        CustomTabsIntent.Builder customtabIntent = new CustomTabsIntent.Builder();
        customtabIntent.setToolbarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        customtabIntent.setShowTitle(true);
        customtabIntent.addDefaultShareMenuItem();
        customtabIntent.setStartAnimations(getActivity(),R.anim.right_in, R.anim.left_out);
        customtabIntent.setExitAnimations(getActivity(),R.anim.left_in,R.anim.right_out);
        Intent copyIntent = new Intent(getActivity(), CopyURLBroadcast.class);
        PendingIntent copypendingIntent = PendingIntent.getBroadcast(getActivity(),0, copyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        customtabIntent.addMenuItem("Copy Link", copypendingIntent);

        if (chromeInstalled()) {
            customtabIntent.build().intent.setPackage("com.android.chrome");
        }
        customtabIntent.build().launchUrl(getActivity(), website);
    }
    private boolean chromeInstalled(){
        try{
            getActivity().getPackageManager().getPackageInfo("com.android.chrome",0);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
