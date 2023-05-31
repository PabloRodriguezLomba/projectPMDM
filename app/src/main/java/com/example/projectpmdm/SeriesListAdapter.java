package com.example.projectpmdm;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SeriesListAdapter extends ArrayAdapter {

    private Activity context;

    private novel[] arraynovel;

    public SeriesListAdapter(@NonNull Activity context, int resource, @NonNull novel[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.arraynovel = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView ;
        ViewHolder viewHolder;
        if (fila == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            fila = layoutInflater.inflate(R.layout.serielist,null);
            viewHolder = new ViewHolder();

            viewHolder.txtNombre = fila.findViewById(R.id.serieListName);
            viewHolder.imgFoto = fila.findViewById(R.id.serieslistImage);
            viewHolder.rtbStar = fila.findViewById(R.id.ratingBarSeriesList);
            viewHolder.ratingNumber = fila.findViewById(R.id.txtRatingSeriesList);
            fila.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) fila.getTag();
        }





        viewHolder.txtNombre.setText(arraynovel[position].getNombre());
        viewHolder.imgFoto.setImageResource(arraynovel[position].getImage());
        viewHolder.rtbStar.setNumStars(arraynovel[position].getRating());
        viewHolder.ratingNumber.setText(arraynovel[position].getRating().toString());




        return fila;
    }

    private static class ViewHolder{
        TextView    txtNombre;
        ImageView imgFoto;
        RatingBar rtbStar;
        TextView ratingNumber;
    }
}
