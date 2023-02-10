package com.example.project;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.nio.charset.StandardCharsets;

public class DataBaseHelper  extends SQLiteOpenHelper {


    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE DESTINATION(id INTEGER PRIMARY KEY AUTOINCREMENT, city TEXT,country TEXT,continent TEXT,longitude DOUBLE,latitude DOUBLE,cost INTEGER,img TEXT,description TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE USERS(Email TEXT PRIMARY KEY,Password TEXT, FirstName TEXT,LastName TEXT,Fcontinent TEXT)");

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


    public void insertDestinations(Destination destination){

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues ContentValues = new ContentValues();
            ContentValues.put("city", destination.getCity());
            ContentValues.put("country", destination.getCountry());
            ContentValues.put("continent", destination.getContinent());
            ContentValues.put(" longitude", destination.getLongitude());
            ContentValues.put(" latitude", destination.getLatitude());
            ContentValues.put("cost", destination.getCost());
            ContentValues.put("img", destination.getImage());
            ContentValues.put("description", destination.getDescription());

            sqLiteDatabase.insert("DESTINATION", null, ContentValues);
        }



    public Cursor All() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT city , country FROM DESTINATION GROUP BY continent",null);
    }

    public Cursor getAllDestinations() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM DESTINATION", null);
    }

    public Cursor getAscending(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT city , country FROM DESTINATION ORDER BY cost", null);
    }

    public Cursor getDescending(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT city , country FROM DESTINATION ORDER BY cost DESC", null);

    }
    }

