package com.example.flipr2transport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class StateCityDatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "StatesAndCitiesIndia.db";
    private static final int DATABASE_VERSION =1;


    public StateCityDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
