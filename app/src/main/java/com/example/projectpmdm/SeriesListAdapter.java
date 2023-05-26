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
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View fila = layoutInflater.inflate(R.layout.serielist,null);

        TextView txtNombre = fila.findViewById(R.id.serieListName);
        ImageView imgFoto = fila.findViewById(R.id.serieslistImage);
        RatingBar rtbStar = fila.findViewById(R.id.ratingBarSeriesList);
        TextView ratingNumber = fila.findViewById(R.id.txtRatingSeriesList);

        txtNombre.setText(arraynovel[position].getNombre());
        imgFoto.setImageURI(Uri.parse(arraynovel[position].getImage()));






        return super.getView(position, convertView, parent);
    }
}
