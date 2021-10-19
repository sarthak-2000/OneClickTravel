package com.example.oneclicktravel.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.oneclicktravel.Fragments.AccountFragment;
import com.example.oneclicktravel.Fragments.EmergencyContactFragment;
import com.example.oneclicktravel.Fragments.HomeFragment;
import com.example.oneclicktravel.Fragments.ShareFragment;
import com.example.oneclicktravel.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    MapsActivity mapsActivity;
    public LatLng latLngn;
    public Double slatitude;
    public Double slongitude;
    String uri;

    Toolbar toolbar;
    private static final String TAG_HOME = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");

        Fragment fragment;
        fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        mapsActivity =new MapsActivity();;

        uri = "http://maps.google.com/maps?saddr=" +slatitude+","+slongitude;

        MyTimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();

        myTimer.schedule(myTask, 5000, 1800000);



    }

    class MyTimerTask extends TimerTask {
        public void run() {

            generateNotification(getApplicationContext(), uri);
        }
    }

    private void generateNotification(Context context, String message) {

        // sendSMS(phoneNo,uri);
        int icon = R.drawable.ic_places;
        long when = System.currentTimeMillis();
        String appname = context.getResources().getString(R.string.app_name);
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        Notification notification;
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, BaseActivity.class), 0);

        // To support 2.3 os, we use "Notification" class and 3.0+ os will use
        // "NotificationCompat.Builder" class.
        if (currentapiVersion < android.os.Build.VERSION_CODES.HONEYCOMB) {
            /*notification = new Notification(icon, message, 0);
            notification.setLatestEventInfo(context, appname, message,
                    contentIntent);
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify((int) when, notification);*/

        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    context);
            notification = builder.setContentIntent(contentIntent)
                    .setSmallIcon(icon).setTicker(appname).setWhen(0)
                    .setAutoCancel(true).setContentTitle("Location Reminder")
                    .setContentText(message).build();

            notificationManager.notify((int) when, notification);

        }
    }

    /*public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

       /* if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // show menu only when home fragment is selected
        //  getMenuInflater().inflate(R.menu.activity_base_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       /* int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_places) {

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;*/

        displaySelectedScreen(item.getItemId());

        return  true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {

            case R.id.nav_home:
                fragment=new HomeFragment();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
                /*
                Intent a=new Intent(this,HomePage.class);
                startActivity(a);*/
                /*Intent a=new Intent(this,MapsActivity.class);
                startActivity(a);
                */break;
            case R.id.nav_places:
                Intent b=new Intent(this, GooglePlacesActivity.class);
                startActivity(b);
               /* fragment = new PlacesFragment();
                getSupportActionBar().setTitle("Places");*/
                break;
            case R.id.nav_account:
                fragment = new AccountFragment();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Account Details");
                break;
            case R.id.nav_share:
                fragment = new ShareFragment();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Share");
                break;
            case R.id.nav_emerg:
                fragment = new EmergencyContactFragment();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Emergency Contacts");
                break;
            case R.id.nav_signout:
                Intent signin=new Intent(BaseActivity.this,HomePage.class);
                startActivity(signin);
                finish();
                break;
            case R.id.nav_aboutus:
//                fragment =new AboutUsFragment();
//                getSupportActionBar().setTitle("About US");
                Intent aboutusi=new Intent(BaseActivity.this,AboutUsActivity.class);
                startActivity(aboutusi);
                break;
            case R.id.nav_privacypolicy:
                Intent privacy=new Intent(BaseActivity.this,PrivacyPolicyActivity.class);
                startActivity(privacy);
                break;

            default:
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }
}
