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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    NovelDatabase nbd;

    ArrayList<chapter> chapterArrayList = new ArrayList<>();

    static SQLiteDatabase db;

    ListView mainActivityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (nbd == null) {
            nbd = new NovelDatabase(MainActivity.this,"NovelsApp",null,1);
        }

        mainActivityList = findViewById(R.id.mainActivityList);



        db = nbd.getWritableDatabase();


       Cursor c = db.rawQuery("SELECT * FROM chapters ",null);
        if (c.moveToFirst()) {
           do {

               String date = c.getString(1);
               String url = c.getString(2);
               String idSeries = c.getString(4);
               String number = c.getString(3);

               chapterArrayList.add(new chapter(date,url,number,idSeries));

           } while (c.moveToNext());
        } else {
            Toast.makeText(this, "not exists usuarios", Toast.LENGTH_SHORT).show();
        }
        c.close();

        chapter[] chaterArray = chapterArrayList.toArray(new chapter[0]);

        ChapterAdapter adapter = new ChapterAdapter(MainActivity.this,R.layout.chapters,chaterArray);
        mainActivityList.setAdapter(adapter);

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
                String nombre = "Lord of the Mysteries";
                int random = new Random().nextInt(7) +1;
                Cursor c = db.rawQuery("SELECT nombre FROM series where id = " + random,null);
                if(c.moveToFirst()) {
                    do {
                        nombre = c.getString(0);
                    } while (c.moveToNext());
                }


                Intent intent = new Intent(MainActivity.this,Series.class);
                intent.putExtra("nombre",nombre);
                startActivity(intent);


                break;
            case R.id.mnSearch:
                Intent is = new Intent(MainActivity.this,SearchSeries.class);
                startActivity(is);
                break;
        }


        return super.onOptionsItemSelected(item);
    }


}