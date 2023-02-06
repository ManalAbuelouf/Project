package com.example.project;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper  extends SQLiteOpenHelper {


    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERS(Email TEXT PRIMARY KEY,Password TEXT, FirstName TEXT,LastName TEXT,Fcontinent TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE DESTINATION(city TEXT PRIMARY KEY,country TEXT,continent TEXT,longitude REAL,latitude REAL,cost INTEGER,img TEXT,description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public void insertUser(Users user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", user.getEmail());
        contentValues.put("Password", user.getPassword());
        contentValues.put("FirstName", user.getFName());
        contentValues.put("LastName", user.getLName());
        contentValues.put("Fcontinent", user.getPContinent());
        sqLiteDatabase.insert("USERS", null, contentValues);
    }
    public Cursor searchUser(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * FROM  USERS WHERE Email=?",new String[]{email},null);
    }
}
