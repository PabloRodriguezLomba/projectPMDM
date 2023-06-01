package com.example.projectpmdm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class chaptersAdapter extends ArrayAdapter {

    Activity context;
    private chapter[] arraynovel;

    public chaptersAdapter(@NonNull Activity context, int resource, @NonNull novel[] objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fila = convertView ;
        chaptersAdapter.ViewHolder viewHolder;
        int number = 1;
        if (fila == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            fila = layoutInflater.inflate(R.layout.serielist,null);
            viewHolder = new chaptersAdapter.ViewHolder();
            viewHolder.Date = fila.findViewById(R.id.chaptersDate);
            viewHolder.url = fila.findViewById(R.id.chaptersUrl);


            fila.setTag(viewHolder);
        } else {
            viewHolder = (chaptersAdapter.ViewHolder) fila.getTag();
        }


        viewHolder.Date.setText(arraynovel[position].getDate());
        viewHolder.url.setText(arraynovel[position].getUrl());
        viewHolder.number.setText(number);






        number++;
        return fila;








    }

    private static class ViewHolder{
        TextView Date;
        TextView url;
        TextView number;


    }
}
