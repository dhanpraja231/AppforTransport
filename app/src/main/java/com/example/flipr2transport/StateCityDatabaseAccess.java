package com.example.flipr2transport;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public String getAddress(String name){
        c = db.rawQuery("select Address from Table1 where Name = '"+name +"'",new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String address = c.getString(0);
            buffer.append(""+address);
        }
        return buffer.toString();


    }
}
