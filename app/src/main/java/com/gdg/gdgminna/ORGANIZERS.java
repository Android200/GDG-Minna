package com.gdg.gdgminna;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ORGANIZERS extends Fragment {
    private View rootView;

    public ORGANIZERS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootView =  inflater.inflate(R.layout.fragment_organizer, container, false);
        final CardView umar = (CardView) rootView.findViewById(R.id.umar);
        final CardView isaac = (CardView) rootView.findViewById(R.id.isaac);
        final CardView gbemileke= (CardView) rootView.findViewById(R.id.gbemileke);
        umar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent us = new Intent (getActivity(),Umar.class);
                ORGANIZERS.this.startActivity(us);
            }
        });

        isaac.setOnClickListener(new View.OnClickListener(){
           @Override
        public void onClick(View v){
               Intent is = new Intent (getActivity(), Isaac.class);
               ORGANIZERS.this.startActivity(is);
           }
        });

        gbemileke.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent is = new Intent (getActivity(), Gbemileke.class);
                ORGANIZERS.this.startActivity(is);
            }
        });

        return rootView;
    }


}
