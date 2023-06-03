package com.example.projectpmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class    SearchSeries extends AppCompatActivity {

    RadioGroup groupGenre;
    RadioGroup grouptype;
    RadioGroup groupLenguage;
    Button btnSearch;
    RadioButton genreNow;
    RadioButton TypeNow;
    RadioButton LenguageNow;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_series);

        btnSearch = findViewById(R.id.btnSearch);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SearchSeries.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        groupGenre = findViewById(R.id.groupGenre);
        groupGenre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                editor.putInt("genre",groupGenre.getCheckedRadioButtonId());
                editor.apply();
            }
        });

        grouptype = findViewById(R.id.groupType);
        grouptype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                editor.putInt("type",grouptype.getCheckedRadioButtonId());
                editor.apply();
            }
        });
        groupLenguage = findViewById(R.id.groupLenguage);
        groupLenguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                editor.putInt("Lenguage",groupLenguage.getCheckedRadioButtonId());
                editor.apply();
            }
        });


        int genre =sharedPreferences.getInt("genre",R.id.Action);
        int type = sharedPreferences.getInt("type",R.id.web);
        int Lenguage = sharedPreferences.getInt("Lenguage",R.id.china);

        groupGenre.check(genre);
        grouptype.check(type);
        groupLenguage.check(Lenguage);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genreNow = findViewById(groupGenre.getCheckedRadioButtonId());
                TypeNow = findViewById(grouptype.getCheckedRadioButtonId());
                LenguageNow = findViewById(groupLenguage.getCheckedRadioButtonId());

                String searchgen = genreNow.getText().toString();
                String searchtyp = TypeNow.getText().toString();
                String searchLen = LenguageNow.getText().toString();



                Intent in = new Intent(SearchSeries.this,searchedList.class);
                in.putExtra("genre",searchgen);
                in.putExtra("typ",searchtyp);
                in.putExtra("Len",searchLen);
                startActivity(in);


            }
        });

    }
}