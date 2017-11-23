package com.example.eddie.librarydbsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eddie on 2017/11/24.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String database = "mydata.db";
    private static final int version = 1;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int versoin){
        super(context, name, factory, version);
    }

    public MyDBHelper(Context context){
        this(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE myTable(_id integer PRIMARY KEY AUTOINCREMENT," + "title text NOT NULL," + "price real NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS myTable");
        onCreate(db);
    }




}
