package com.example.projectpmdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    NovelDatabase nbd;

    ArrayList<chapter> chapterArrayList = new ArrayList<>();

    static SQLiteDatabase db;

    ListView mainActivityList;

    private String dialogText = "";

    private final int notif_alerta = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (nbd == null) {
            nbd = new NovelDatabase(MainActivity.this,"NovelsApp",null,1);
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        mainActivityList = findViewById(R.id.mainActivityList);



        db = nbd.getWritableDatabase();


       Cursor c = db.rawQuery("SELECT * FROM chapters ",null);
        if (c.moveToFirst()) {
           do {

               String date = c.getString(1);
               String url = c.getString(2);
               String idSeries = c.getString(4);
               String number = c.getString(3);

               chapterArrayList.add(new chapter(date,url,number,idSeries));

           } while (c.moveToNext());
        } else {
            Toast.makeText(this, "not exists usuarios", Toast.LENGTH_SHORT).show();
        }
        c.close();

        chapter[] chaterArray = chapterArrayList.toArray(new chapter[0]);

        ChapterAdapter adapter = new ChapterAdapter(MainActivity.this,R.layout.chapters,chaterArray);
        mainActivityList.setAdapter(adapter);
        registerForContextMenu(mainActivityList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all,menu);
        return true;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String elemento=mainActivityList.getAdapter().getItem(info.position).toString();
        menu.setHeaderTitle(elemento);
        inflater.inflate(R.menu.chaptercontext,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnList:
                Intent i = new Intent(MainActivity.this,SeriesList.class);
                startActivity(i);
                break;
            case R.id.mnRandom:
                String nombre = "Lord of the Mysteries";
                int random = new Random().nextInt(7) +1;
                Cursor c = db.rawQuery("SELECT nombre FROM series where id = " + random,null);
                if(c.moveToFirst()) {
                    do {
                        nombre = c.getString(0);
                    } while (c.moveToNext());
                }


                Intent intent = new Intent(MainActivity.this,Series.class);
                intent.putExtra("nombre",nombre);
                startActivity(intent);


                break;
            case R.id.mnSearch:
                Intent is = new Intent(MainActivity.this,SearchSeries.class);
                startActivity(is);
                break;
            case R.id.act_button_remind:

                Notification.Builder builder = new Notification.Builder(this);
                builder.setSmallIcon(R.drawable.white_tower_symbol);
                builder.setTicker("Informacion");
                builder.setContentTitle("Informacion");
                builder.setContentText("Este projecto esta basado en una pagina web real, su uso es el de monitorizar y archivar las novelas que estan subidas a internet sirviendo como un centro o hub para que los usuarios puedan descubrir y leer dichas novelas.  ");
                Bitmap largeICon = BitmapFactory.decodeResource(getResources(),R.drawable.white_tower_symbol);
                builder.setLargeIcon(largeICon);

                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification=builder.build();
                nm.notify(notif_alerta,notification);

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnInternet:
                AdapterView.AdapterContextMenuInfo id = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                chapter chapt  = (chapter) mainActivityList.getAdapter().getItem(id.position);
                String url = chapt.url;
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                i.putExtra(SearchManager.QUERY,url);
                startActivity(i);
                break;
            case R.id.mnReport:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Report");


                View mView = getLayoutInflater().inflate(R.layout.textinputdial,null);

                final EditText input = mView.findViewById(R.id.input);



                builder.setView(mView);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogText = input.getText().toString();
                        AdapterView.AdapterContextMenuInfo id = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        chapter chapt  = (chapter) mainActivityList.getAdapter().getItem(id.position);
                        String idSeries = chapt.idseries;
                        ContentValues nuevoRegistro = new ContentValues();
                        nuevoRegistro.put("idchapter",idSeries);
                        nuevoRegistro.put("report",dialogText);


                        MainActivity.db.insert("report",null,nuevoRegistro);

                        Toast.makeText(MainActivity.this,"Reporte Recibido Gracias por la notificacion ",Toast.LENGTH_SHORT).show();


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