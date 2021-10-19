package com.example.oneclicktravel.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oneclicktravel.Activities.MapsActivity;
import com.example.oneclicktravel.R;
import com.google.android.gms.maps.model.LatLng;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ShareFragment extends Fragment {


    MapsActivity mapsActivity;
    public LatLng latLngn;
    public Double slatitude;
    public Double slongitude;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        view=inflater.inflate(R.layout.fragment_share,container,false);

        mapsActivity=new MapsActivity();
        shareIt();
        return  view;
    }

    private void shareIt() {
//sharing implementation here
        String uri = "http://maps.google.com/maps?saddr=" +slatitude+","+slongitude;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Current Location");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, uri);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    // https://maps.googleapis.com/maps/api/staticmap?center=C.U.Shah,Kothariya,+Surendranagar&zoom=13&scale=2&size=600x300&maptype=roadmap&format=png&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:1%7CC.U.Shah,Kothariya,+Surendranagar
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
