package com.example.projectpmdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NovelDatabase nbd;

    ArrayList<novel> novelArray = new ArrayList<>();

    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (nbd == null) {
            nbd = new NovelDatabase(MainActivity.this,"NovelsApp",null,1);
        }


        novelArray.add(new novel("Lord of the Mysteries",R.drawable.imagent,"Web novel","Action","Cuttlefish that loves diving","Chinese",5));
        novelArray.add(new novel("My House of Horrors", R.drawable.myhouseofhorrors,"Web novel","Horror","I fix air conditioners","Chinese",5));
        novelArray.add(new novel("I Shall Seal The Heavens", R.drawable.issth,"Published novel","Xianxia","Er sen","Chinese",4));




         db = nbd.getWritableDatabase();

        ContentValues nSeries = null;
        for (int i = 0;i < novelArray.size();i++) {
            nSeries = new ContentValues();
            nSeries.put("nombre",novelArray.get(i).getNombre());
            nSeries.put("img",novelArray.get(i).getImage());
            nSeries.put("type",novelArray.get(i).getType());
            nSeries.put("genre",novelArray.get(i).getGenre());
            nSeries.put("author",novelArray.get(i).getAuthor());
            nSeries.put("Lenguage",novelArray.get(i).getLenguage());
            nSeries.put("Rating",novelArray.get(i).getRating());
            db.insert("series",null,nSeries);
        }
       Cursor c = db.rawQuery("SELECT nombre FROM series",null);
        if (c.moveToFirst()) {
           do {
               String n = c.getString(0);
               Toast.makeText(this, "Nombre: " + n, Toast.LENGTH_SHORT).show();
           } while (c.moveToNext());
        } else {
            Toast.makeText(this, "not exists usuarios", Toast.LENGTH_SHORT).show();
        }
        c.close();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnList:
                Intent i = new Intent(MainActivity.this,SeriesList.class);
                startActivity(i);
                break;
            case R.id.mnRandom:
                break;
            case R.id.mnSearch:
                Intent is = new Intent(MainActivity.this,SearchSeries.class);
                startActivity(is);
                break;
        }


        return super.onOptionsItemSelected(item);
    }


}