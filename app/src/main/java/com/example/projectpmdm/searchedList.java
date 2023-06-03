package com.example.projectpmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class searchedList extends AppCompatActivity {

    ArrayList<novel> arnovel = new ArrayList<>();
    ListView searchedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_list);

        searchedList = findViewById(R.id.searchedList);

        Intent intent = getIntent();

        String gen =  intent.getStringExtra("genre");
        String len = intent.getStringExtra("Len");
        String typ = intent.getStringExtra("typ");


        Cursor c = MainActivity.db.rawQuery("SELECT * FROM series WHERE genre = '" +gen+"' and type='"+typ+"' and Lenguage='"+len+"'",null);
        if (c.moveToFirst()) {
            do{
                arnovel.add(new novel(c.getString(1),c.getInt(2),c.getInt(7)));
            }while(c.moveToNext());
        }

        novel[] nar = arnovel.toArray(new novel[0]);

        SeriesListAdapter adapter = new SeriesListAdapter(searchedList.this,R.layout.serielist,nar);
        searchedList.setAdapter(adapter);
        searchedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                novel nuv = (novel) parent.getItemAtPosition(position);
                String nombre = nuv.getNombre();
                Intent intent = new Intent(searchedList.this,Series.class);
                intent.putExtra("nombre",nombre);
                startActivity(intent);

            }
        });

    }
}