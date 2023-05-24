package com.example.projectpmdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NovelDatabase extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE if not exists Series (id INTEGER PRIMARY KEY AUTOINCREMENT , nombre TEXT NOT NULL,image BLOB NOT NULL,type TEXT NOT NULL,genre TEXT NOT NULL,author TEXT NOT NULL)";
    String sqlChapters = "CREATE TABLE IF NOT EXISTS chapters (id INTEGER PRIMARY KEY AUTOINCREMENT ,date TEXT NOT NULL,url TEXT NOT NULL,idSeries INTEGER NOT NULL,FOREIGN KEY(idSeries) REFERENCES Series(id))";


    public NovelDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL(sqlChapters);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
