package com.example.projectpmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Series extends AppCompatActivity {

    novel currentnovel;
    TextView SeriesName;
    ImageView imagent;
    TextView txtSeriesType;
    TextView txtSeriesGenre;
    TextView txtSeriesAuthor;
    TextView txtSeriesLenguage;
    ListView seriesChapterList;
    ArrayList<chapter> chapterlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        int id = 1;
        SeriesName = findViewById(R.id.SeriesName);
        imagent = findViewById(R.id.SeriesImg);
        txtSeriesType = findViewById(R.id.txtSeriesType);
        txtSeriesGenre = findViewById(R.id.txtSeriesGenre);
        txtSeriesAuthor = findViewById(R.id.txtSeriesAuthor);
        txtSeriesLenguage = findViewById(R.id.txtSeriesLenguage);
        seriesChapterList = findViewById(R.id.seriesChapterList);

        Intent intent = getIntent();
        String nomb = intent.getStringExtra("nombre");

        Cursor c = MainActivity.db.rawQuery("SELECT * FROM series where nombre='" + nomb + "'",null);
        if (c.moveToFirst()) {
            do {
                id = c.getInt(0);
                currentnovel = new novel(c.getString(1),c.getInt(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getFloat(7));
            } while (c.moveToNext());
        }
        c.close();
        SeriesName.setText(currentnovel.getNombre());
        imagent.setImageResource(currentnovel.getImage());
        txtSeriesType.setText(currentnovel.getType());
        txtSeriesGenre.setText(currentnovel.getGenre());
        txtSeriesAuthor.setText(currentnovel.getAuthor());
        txtSeriesLenguage.setText(currentnovel.getLenguage());

        Cursor cur = MainActivity.db.rawQuery("SELECT * FROM chapters where idSeries=" + id  ,null);
        if (cur.moveToFirst()) {
            do {
                chapterlist.add(new chapter(cur.getString(1),cur.getString(2),cur.getString(3)));
            } while (cur.moveToNext());
        }
        cur.close();

        chapter[] chap = chapterlist.toArray(new chapter[0]);

       ChapterAdapter adapter = new ChapterAdapter(Series.this,R.layout.chapters,chap);
       seriesChapterList.setAdapter(adapter);



    }
}