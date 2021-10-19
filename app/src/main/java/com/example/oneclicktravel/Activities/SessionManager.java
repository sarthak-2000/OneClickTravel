package com.example.oneclicktravel.Activities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SHAH on 06/01/2017.
 */

public class SessionManager {

    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("App_sharedcontent", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }



    public String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("App_sharedcontent",	Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }


}
