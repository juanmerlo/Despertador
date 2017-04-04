package com.juanmartin.despertador;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    ListView alarmasLista;
    ItemAlarma[] itemsAlarmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AgregarAlarmaActivity.class);
                int requestCode = 1; // Or some number you choose
                startActivityForResult(intent, requestCode);
            }
        });

        inicializarLista();
        iniciarServicio();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // Collect data from the intent and use it
        if(requestCode == 1){
            if(data != null && data.getStringExtra("valor").equals("ok")){
                inicializarLista();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void inicializarLista(){
        alarmasLista = (ListView) findViewById(R.id.alarmasLista);
        Handler_sqlite helper = new Handler_sqlite(this);
        helper.abrir();
        itemsAlarmas = helper.dameAlarmas();
        helper.close();
        ItemAlarmaAdapter adapter = new ItemAlarmaAdapter(this,R.layout.listview_item_row,itemsAlarmas);
        alarmasLista.setAdapter(adapter);
    }

    public void iniciarServicio(){

    }



}
