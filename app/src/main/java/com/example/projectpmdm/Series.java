package com.example.projectpmdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

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
    String rep;
    private String dialogText = "";

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
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
                chapterlist.add(new chapter(cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4)));
            } while (cur.moveToNext());
        }
        cur.close();

        chapter[] chap = chapterlist.toArray(new chapter[0]);

       ChapterAdapter adapter = new ChapterAdapter(Series.this,R.layout.chapters,chap);
       seriesChapterList.setAdapter(adapter);
       registerForContextMenu(seriesChapterList);


       seriesChapterList.setOnTouchListener(new ListView.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               int action = event.getAction();
               switch (action) {
                   case MotionEvent.ACTION_DOWN:
                       // Disallow ScrollView to intercept touch events.
                       v.getParent().requestDisallowInterceptTouchEvent(true);
                       break;
                   case MotionEvent.ACTION_UP:
                       // Allow ScrollView to intercept touch events.
                       v.getParent().requestDisallowInterceptTouchEvent(false);
                       break;
               }
               // Handle ListView touch events.
               v.onTouchEvent(event);
               return true;
           }
       });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rest,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnrestBack:
                finish();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String elemento=seriesChapterList.getAdapter().getItem(info.position).toString();
        menu.setHeaderTitle(elemento);
        inflater.inflate(R.menu.chaptercontext,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnInternet:
                AdapterView.AdapterContextMenuInfo id = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                chapter chapt  = (chapter) seriesChapterList.getAdapter().getItem(id.position);
                String url = chapt.url;
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                i.putExtra(SearchManager.QUERY,url);
                startActivity(i);
                break;
            case R.id.mnReport:

                AlertDialog.Builder builder = new AlertDialog.Builder(Series.this);
                builder.setTitle("Report");


                View mView = getLayoutInflater().inflate(R.layout.textinputdial,null);

                final EditText input = mView.findViewById(R.id.input);



                builder.setView(mView);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogText = input.getText().toString();
                        AdapterView.AdapterContextMenuInfo id = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        chapter chapt  = (chapter) seriesChapterList.getAdapter().getItem(id.position);
                        String idSeries = chapt.idseries;
                        ContentValues nuevoRegistro = new ContentValues();
                        nuevoRegistro.put("idchapter",idSeries);
                        nuevoRegistro.put("report",dialogText);


                        MainActivity.db.insert("report",null,nuevoRegistro);

                        Toast.makeText(Series.this,getString(R.string.toastReport),Toast.LENGTH_SHORT).show();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();


                break;


        }

        return super.onContextItemSelected(item);



    }
}