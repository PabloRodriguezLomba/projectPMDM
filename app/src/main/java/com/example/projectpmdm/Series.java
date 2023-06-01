package com.example.projectpmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Series extends AppCompatActivity {

    novel currentnovel;
    TextView SeriesName;
    ImageView imagent;
    TextView txtSeriesType;
    TextView txtSeriesGenre;
    TextView txtSeriesAuthor;
    TextView txtSeriesLenguage;
    ListView seriesChapterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

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
                currentnovel = new novel(c.getString(1),c.getInt(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getFloat(7));
            } while (c.moveToNext());
        }

        SeriesName.setText(currentnovel.getNombre());
        imagent.setImageResource(currentnovel.getImage());
        txtSeriesType.setText(currentnovel.getType());
        txtSeriesGenre.setText(currentnovel.getGenre());
        txtSeriesAuthor.setText(currentnovel.getAuthor());
        txtSeriesLenguage.setText(currentnovel.getLenguage());

    }
}