package com.gdg.gdgminna;


import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CONTACT_US extends Fragment {
    private View rootView;


    public CONTACT_US() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_contact__us, container, false);
        CardView group = (CardView) rootView.findViewById(R.id.groups);
        CardView plus = (CardView) rootView.findViewById(R.id.plus);
        CardView facebook = (CardView) rootView.findViewById(R.id.facebook);
        CardView twitter = (CardView) rootView.findViewById(R.id.twitter);
        CardView instagram = (CardView) rootView.findViewById(R.id.instagram);
        CardView youtube = (CardView) rootView.findViewById(R.id.youtube);
        CardView developers = (CardView) rootView.findViewById(R.id.developers);
        CardView women = (CardView) rootView.findViewById(R.id.women);
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("http://groups.google.com/d/forum/gdg-minna");
                //String url="http://groups.google.com/d/forum/gdg-minna";
                //Intent i= new Intent(Intent.ACTION_VIEW);
                //i.setData(Uri.parse(url));
                //startActivity(i);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://plus.google.com/b/113224920428716041640/+GdgminnaBlogspot/posts");
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://www.facebook.com/GDGMinna");
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://twitter.com/GDGMinna");
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://www.instagram.com/gdg_minna/");
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://www.youtube.com/channel/UCTYCALPn-1LCHm6IP6jIdLw");
            }
        });
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://developers.google.com/groups/chapter/113224920428716041640/");
            }
        });
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeninCustomTab("https://www.womentechmakers.com/");
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
