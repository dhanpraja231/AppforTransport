package com.example.flipr2transport;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StateCityDatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static StateCityDatabaseAccess instance;
     Cursor c = null;

    private StateCityDatabaseAccess(Context context){
        this.openHelper = new StateCityDatabaseOpenHelper(context);
    }

    public static StateCityDatabaseAccess getInstance(Context context) {
        if(instance==null){
            instance = new StateCityDatabaseAccess((context));
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();

    }

    public void close(){
        if(db!= null){
            this.db.close();
        }
    }


    public ArrayList<String> getCities(String state){
        c = db.rawQuery("select City from Main where State = '"+state +"'",new String[]{});
        StringBuffer buffer = new StringBuffer();
        ArrayList<String> result = new ArrayList<>(20);
        while(c.moveToNext()){
            result.add(c.getString(0));
        }
        return result;


    }
}
