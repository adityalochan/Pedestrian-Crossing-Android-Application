package com.example.android.pedestriancrossing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adityalochan on 2/24/2018.
 */


public class CrossStreetDBHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "test2.db";
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "signal";

    public static final String ID = "id";
    public static final String SIGNAL_NAME = "signalName";
    public static final String CROSSING_COUNT = "crossingCount";
    public static final String CROSSING1 = "crossing1";
    public static final String CROSSING2 = "crossing2";
    public static final String CROSSING3 = "crossing3";
    public static final String CROSSING4 = "crossing4";
    public static final String COORDINATE_X = "coordinateX";
    public static final String COORDINATE_Y = "coordinateY";

    private SQLiteDatabase myDB1;

    //constructor
    public CrossStreetDBHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryTable = "CREATE TABLE " + TABLE_NAME +
                "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SIGNAL_NAME + " TEXT NOT NULL, " +
                CROSSING_COUNT + " INTEGER NOT NULL, " +
                CROSSING1 + " TEXT NOT NULL, " +
                CROSSING2 + " TEXT NOT NULL, " +
                CROSSING3 + " TEXT NOT NULL, " +
                CROSSING4 + " TEXT NOT NULL, " +
                COORDINATE_X + " REAL NOT NULL, " +
                COORDINATE_Y + " REAL NOT NULL " +
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
        myDB1 = getWritableDatabase();
    }
    //closing database connection
    public void closeDB(){
        if(myDB1 != null && myDB1.isOpen()){
            myDB1.close();
        }
    }

    // inserting values in database
    public long insert(String sName, int cCount, String C1, String C2, String C3, String C4, Float coordX, Float coordY){
        ContentValues values = new ContentValues();

        values.put(SIGNAL_NAME, sName);
        values.put(CROSSING_COUNT, cCount);
        values.put(CROSSING1, C1);
        values.put(CROSSING2, C2);
        values.put(CROSSING3, C3);
        values.put(CROSSING4, C4);
        values.put(COORDINATE_X, coordX);
        values.put(COORDINATE_Y, coordY);

        return getReadableDatabase().insert(TABLE_NAME, null, values);
    }

    // method to get all records from the table
    public Cursor getAllRecords(){

        // myDB1.query(TABLE_NAME, null, null, null, null, null, null);
        String query = "SELECT * FROM " + TABLE_NAME;
        return myDB1.rawQuery(query, null);
    }

    public Cursor getDataBasedOnQuery(String query){
        return getReadableDatabase().rawQuery(query, null);
    }

}
