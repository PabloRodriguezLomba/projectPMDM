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





        db = nbd.getWritableDatabase();


       Cursor c = db.rawQuery("SELECT nombre FROM series ",null);
        if (c.moveToFirst()) {
           do {
               String n = c.getString(0);
               System.out.println(n);
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