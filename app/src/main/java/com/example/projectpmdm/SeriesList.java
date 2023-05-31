package com.example.projectpmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SeriesList extends AppCompatActivity {

    ArrayList<novel> arnovel = new ArrayList<>();

    ListView seriesListList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        seriesListList = findViewById(R.id.seriesListList);

        Cursor c = MainActivity.db.rawQuery("SELECT nombre , image,Rating FROM series",null);
        if (c.moveToFirst()) {
            do {
                String nombre = c.getString(0);
                int img = c.getInt(1);
                int rating = c.getInt(2);
                arnovel.add(new novel(nombre,img,rating));
            } while(c.moveToNext());
        }
        c.close();

        novel[] arraynv =arnovel.toArray(new novel[0]);

        SeriesListAdapter adapter = new SeriesListAdapter(SeriesList.this,R.layout.serielist,arraynv);
        seriesListList.setAdapter(adapter);

    }
}