package com.example.android.pedestriancrossing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adityalochan on 2/22/2018.
 */


public class LoginRegistrationDBHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "test1.db";
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "registration";

    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String UNIVERSITY_ID = "uID";
    public static final String PASSWORD = "password";
    public static final String EMAIL_ID = "emailID";

    private SQLiteDatabase myDB;

    //constructor
    public LoginRegistrationDBHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryTable = "CREATE TABLE " + TABLE_NAME +
                "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIRST_NAME + " TEXT NOT NULL, " +
                LAST_NAME + " TEXT NOT NULL, " +
                UNIVERSITY_ID + " INTEGER NOT NULL, " +
                PASSWORD + " TEXT NOT NULL, " +
                EMAIL_ID + " TEXT NOT NULL " +
                ")";

        db.execSQL(queryTable);
    }



    // recreating database if required by replacing old version value with new one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // recreating database
        onCreate(db);
    }

    // opening database connection
    public void openDB(){
        myDB = getWritableDatabase();
    }
    //closing database connection
    public void closeDB(){
        if(myDB != null && myDB.isOpen()){
            myDB.close();
        }
    }

    // inserting values in database
    public long insert(String fName, String lName,int uid, String password, String emailID){
        ContentValues values = new ContentValues();

            values.put(FIRST_NAME, fName);
            values.put(LAST_NAME, lName);
            values.put(UNIVERSITY_ID, uid);
            values.put(PASSWORD, password);
            values.put(EMAIL_ID, emailID);

        return myDB.insert(TABLE_NAME, null, values);
    }

    // method to get all records from the table
    public Cursor getAllRecords(){

        // myDB.query(TABLE_NAME, null, null, null, null, null, null);
        String query = "SELECT * FROM " + TABLE_NAME;
        return myDB.rawQuery(query, null);
    }

    public Cursor getDataBasedOnQuery(String query){
        return myDB.rawQuery(query, null);
    }

}
