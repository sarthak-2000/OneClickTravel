package com.example.oneclicktravel.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oneclicktravel.Activities.MapsActivity;
import com.example.oneclicktravel.Activities.SessionManager;
import com.example.oneclicktravel.Activities.WeatherActivity;
import com.example.oneclicktravel.R;
import com.example.oneclicktravel.mtplview.MtplButton;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;



public class HomeFragment extends Fragment {

    //Button btn_logout;
    SessionManager sessionManager;
    TextView tv_open_map;
    MtplButton btn_weather;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    MapsActivity mapsActivity;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  return super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_home,container,false);

        sessionManager=new SessionManager();
        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will
                // execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_weather=(MtplButton)view.findViewById(R.id.btn_weather);
        btn_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent weather=new Intent(getContext(), WeatherActivity.class);
                startActivity(weather);
            }
        });

        tv_open_map=(TextView)view.findViewById(R.id.tv_open_map);

        tv_open_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mapactivity=new Intent(getContext(), MapsActivity.class);
                startActivity(mapactivity);

            }
        });

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
