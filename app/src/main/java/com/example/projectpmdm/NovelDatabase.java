package com.example.projectpmdm;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class NovelDatabase extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE if not exists series (id INTEGER PRIMARY KEY AUTOINCREMENT , nombre TEXT NOT NULL,image INTEGER NOT NULL,type TEXT NOT NULL,genre TEXT NOT NULL,author TEXT NOT NULL,Lenguage TEXT NOT NULL,Rating INTEGER NOT NULL)";
    String sqlChapters = "CREATE TABLE IF NOT EXISTS chapters (id INTEGER PRIMARY KEY AUTOINCREMENT ,date TEXT NOT NULL,url TEXT NOT NULL,idSeries INTEGER NOT NULL,FOREIGN KEY(idSeries) REFERENCES series(id))";

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
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('Lord of the Mysteries',2131165407,'Web novel','Action','Cuttlefish that loves diving','Chinese',5)");
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('My House of Horrors',2131165409,'Web novel','Horror','I fix air conditioners','Chinese',5)");
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('I Shall Seal The Heavens',2131165408,'Web novel','Xianxia','Er sen','Chinese',4)");
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('Circle of Inevitability',2131165410,'Web novel','Action','Cuttlefish that loves diving','Chinese',5)");
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('Deathbound duke´s daughter',2131165411,'Light novel','Fantasy','Terasu Senoo','Japanese',4.6)");
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('Grandmaster of Demonic Cultivation',2131165412,'Web novel','Romance','Mò Xiāng Tóngxiù','Chinese',4.7)");
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('Volcanicage',2131165414,'Web novel','wuxia','jung joon shin','Korean',4.5)");
            db.execSQL("INSERT INTO series (nombre,image,type,genre,author,Lenguage,Rating) VALUES ('Martial World',2131165413,'Web novel','Fantasy','Cocooned Cow','Chinese',4)");

            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-1',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-2',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-3',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-4',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-5',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-6',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-7',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-8',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-9',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-10',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-11',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-12',1)");
            db.execSQL("INSERT INTO chapters (date,url,idSeries) VALUES(" + Calendar.getInstance().getTime().toString() + ",'https://wuxia.click/chapter/lord-of-the-mysteries-13',1)");


        } catch (SQLException e) {
            Toast.makeText(context, "ERROR EN LA INSERCION EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
