package com.example.oneclicktravel.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.oneclicktravel.R;

public class SplashActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        sessionManager=new SessionManager();

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 4 seconds
                    sleep(4*1000);


                    String status=sessionManager.getPreferences(SplashActivity.this,"status");

                    Log.i("Ch",status);
                    if (status.equals("1")){
                        Intent i=new Intent(SplashActivity.this,BaseActivity.class);
                        startActivity(i);
                    }else{
                        Intent i=new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(i);
                    }

                    finish();

                } catch (Exception e) {

                }
            }
        };

        background.start();

    }


}
