package com.example.oneclicktravel.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.oneclicktravel.R;



public class EmergencyContactFragment extends Fragment implements View.OnClickListener {
    View view;
    LinearLayout lnr_fire,lnr_ambulance,lnr_police,lnr_women,lnr_disaster,lnr_hospitals,lnr_information;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_emergency_contact,container,false);
        init();
        return  view;
    }

    public void init(){
        lnr_police=(LinearLayout)view.findViewById(R.id.lnr_police);
        lnr_police.setOnClickListener(this);
        lnr_fire=(LinearLayout)view.findViewById(R.id.lnr_fire);
        lnr_fire.setOnClickListener(this);
        lnr_ambulance=(LinearLayout)view.findViewById(R.id.lnr_ambulance);
        lnr_ambulance.setOnClickListener(this);
        lnr_disaster=(LinearLayout)view.findViewById(R.id.lnr_disaster);
        lnr_disaster.setOnClickListener(this);
        lnr_hospitals=(LinearLayout)view.findViewById(R.id.lnr_hospitals);
        lnr_hospitals.setOnClickListener(this);
        lnr_women=(LinearLayout)view.findViewById(R.id.lnr_womenDanger);
        lnr_women.setOnClickListener(this);
        lnr_information=(LinearLayout)view.findViewById(R.id.lnr_information);
        lnr_information.setOnClickListener(this);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {

        int id=view.getId();
        switch (id){
            case R.id.lnr_police:
                /*String num="100";
                String number = "tel:" + num;*/
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "101"));
                startActivity(intent);
                break;
            case R.id.lnr_fire:
                Intent intentf = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "101"));
                startActivity(intentf);
                break;
            case R.id.lnr_ambulance:
                Intent intenta = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "102"));
                startActivity(intenta);
                break;
            case R.id.lnr_womenDanger:
                Intent intentw = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "181"));
                startActivity(intentw);
                break;
            case R.id.lnr_hospitals:
                Intent intenth = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "1059"));
                startActivity(intenth);
                break;
            case R.id.lnr_information:
                Intent intenti = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "107"));
                startActivity(intenti);
                break;
            case R.id.lnr_disaster:
                Intent intentd = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "108"));
                startActivity(intentd);
                break;
            default:
                break;
        }
    }
}