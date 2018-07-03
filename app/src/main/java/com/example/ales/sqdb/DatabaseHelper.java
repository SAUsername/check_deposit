package com.example.ales.sqdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ales on 3.7.2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "sqdb.DatabaseHelper";

    private static final String TABLE_NAME = "maan";
    private static final String COL_0 = "ID";
    private static final String COL_1 = "name";
    private static final String COL_2 = "price";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_1 + " TEXT, " + COL_2 + " DECIMAL(5, 2))";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, Float price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();

        cV.put(COL_1, item);
        cV.put(COL_2, price);

        Log.d(TAG, "addData: Adding " + item + " with price " + price + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, cV);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
