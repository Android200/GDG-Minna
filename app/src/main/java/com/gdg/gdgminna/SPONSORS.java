package com.gdg.gdgminna;


import android.app.PendingIntent;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SPONSORS extends Fragment {
private View rootView;

    public SPONSORS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_sponsor, container, false);
        CardView google = (CardView) rootView.findViewById(R.id.google);
        CardView developers = (CardView) rootView.findViewById(R.id.developers);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://www.google.com");
                //String url="https://www.google.com";
                //Intent i= new Intent(Intent.ACTION_VIEW);
                //i.setData(Uri.parse(url));
                //startActivity(i);
            }
        });

        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://developers.google.com");
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


