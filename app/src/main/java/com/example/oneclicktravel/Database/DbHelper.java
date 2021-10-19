package com.example.oneclicktravel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.oneclicktravel.Model.UserData;

import java.util.ArrayList;
import java.util.List;



public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper mDbHelper;
    private static final String TAG = "DbHelper";
    // Database Info
    private static final String DATABASE_NAME = "Register";
    private static final int DATABASE_VERSION = 1;

    //Table Names
    private static final String TABLE_USERdETAIL = "tbl_travelaide_register";


    //userdetail Table Columns
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String CONTACT = "contact";

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public static synchronized DbHelper getInstance(Context context) {

        if (mDbHelper == null) {
            mDbHelper = new DbHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String REGISTRATION_TABLE = "CREATE TABLE " + TABLE_USERdETAIL +
                "(" +
                ID + " INTEGER PRIMARY KEY ," +
                NAME + " TEXT," +
                EMAIL + " TEXT," +
                PASSWORD + " TEXT," +
                CONTACT + " TEXT" +
                ")";

        db.execSQL(REGISTRATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_USERdETAIL);
        onCreate(sqLiteDatabase);
    }


    public void insertUserDetail(UserData userData) {

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(NAME, userData.name);
            values.put(EMAIL, userData.email);
            values.put(PASSWORD,userData.password);
            values.put(CONTACT, userData.contact);


            db.insertOrThrow(TABLE_USERdETAIL, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add post to database");
        } finally {

            db.endTransaction();
        }

    }


    /*
   fetch all data from UserTable
    */

    public List<UserData> getAllUser() {

        List<UserData> usersdetail = new ArrayList<>();

        String USER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_USERdETAIL;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(USER_DETAIL_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    UserData userData = new UserData();

                    userData.setName(cursor.getString(1));
                    userData.setEmail(cursor.getString(2));
                    userData.setPassword(cursor.getString(3));
                    userData.setContact(cursor.getString(4));

                    usersdetail.add(userData);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return usersdetail;

    }


    public String getSingleUserData(String email)
    {
        SQLiteDatabase db=getReadableDatabase();

        Cursor cursor=db.query(TABLE_USERdETAIL,null,EMAIL+"=?",new String[]{email},null,null,null);
        String storedpassword="";

        try{
            if(cursor.getCount()<1)
            {
                cursor.close();
                return "User Doesnot Exist";
            }
            cursor.moveToFirst();
            storedpassword=cursor.getString(cursor.getColumnIndex(PASSWORD));
            cursor.close();
        }catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        
        return storedpassword;
    }




}
