package com.example.projectpmdm;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NovelDatabase extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE if not exists series (id INTEGER PRIMARY KEY AUTOINCREMENT , nombre TEXT NOT NULL,image INTEGER NOT NULL,type TEXT NOT NULL,genre TEXT NOT NULL,author TEXT NOT NULL,Lenguage TEXT NOT NULL,Rating INTEGER NOT NULL)";
    String sqlChapters = "CREATE TABLE IF NOT EXISTS chapters (id INTEGER PRIMARY KEY AUTOINCREMENT ,date TEXT NOT NULL,url TEXT NOT NULL,idSeries INTEGER NOT NULL,FOREIGN KEY(idSeries) REFERENCES Series(id))";

    Context context;

    public NovelDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL(sqlChapters);

        try {
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('Lord of the Mysteries',3,'Web novel','Action','Cuttlefish that loves diving','Chinese',5)");
        } catch (SQLException e) {
            Toast.makeText(context, "ERROR EN LA INSERCION EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
