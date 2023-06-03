package com.example.projectpmdm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class ChapterAdapter extends ArrayAdapter {

    private Activity context;
    private chapter[] arrayChapters;


    public ChapterAdapter(@NonNull Activity context, int resource, @NonNull chapter[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayChapters = objects;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(R.layout.chapters,null);

        TextView Date = fila.findViewById(R.id.chaptersDate);
        TextView Url = fila.findViewById(R.id.chaptersUrl);
        TextView number = fila.findViewById(R.id.chaptersNumber);


        Date.setText(arrayChapters[position].getDate());
        Url.setText(arrayChapters[position].getUrl());
        number.setText(arrayChapters[position].getNumber());


        return fila;
    }


}
