package com.juanmartin.despertador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import static android.provider.BaseColumns._ID;

/**
 * Created by juanmartin on 2/8/2016.
 */
public class Handler_sqlite extends SQLiteOpenHelper{

    public Handler_sqlite(Context context) {
        super(context, "Despertador", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE alarmas ("+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

        " horario TEXT, dias TEXT, nombre TEXT,hora INTEGER, minutos INTEGER, semana TEXT, habilitado INTEGER," +
                " domingo INTEGER," +
                " lunes INTEGER," +
                " martes INTEGER," +
                " miercoles INTEGER," +
                " jueves INTEGER," +
                " viernes INTEGER," +
                " sabado INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS alarmas;");
        onCreate(db);

    }

    public void insertarRegistro(String horario,
                                 String dias,
                                 String nombre,
                                 int hora,
                                 int minutos,
                                 int habiliado,
                                 int domingo,
                                 int lunes,
                                 int martes,
                                 int miercoles,
                                 int jueves,
                                 int viernes,
                                 int sabado){

        ContentValues valores = new ContentValues();
        valores.put("horario" , horario);
        valores.put("dias", dias);
        valores.put("nombre", nombre);
        valores.put("hora", hora);
        valores.put("minutos", minutos);
        valores.put("habilitado", habiliado);
        valores.put("domingo",domingo);
        valores.put("lunes",lunes);
        valores.put("martes",martes);
        valores.put("miercoles",miercoles);
        valores.put("jueves",jueves);
        valores.put("viernes",viernes);
        valores.put("sabado",sabado);

        this.getWritableDatabase().insert("alarmas",null,valores);

    }

    public void borrarRegistro(int id){
        String query = "DELETE FROM alarmas WHERE " + _ID + "=" + id + ";";
        //String query = "DELETE FROM alarmas WHERE 1=1";

        this.getWritableDatabase().execSQL(query);

    }

    public int dameConteoRegistros(){

        String columnas[] = {_ID,"horario","dias","nombre","habilitado"};
        Cursor c = this.getWritableDatabase().query("alarmas",columnas,null,null,null,null,null);

        return c.getCount();
    }

    public ItemAlarma[] dameAlarmas(){

        String columnas[] = {_ID,"horario","dias","nombre","habilitado","domingo","lunes","martes","miercoles","jueves","viernes","sabado"};
        Cursor c = this.getReadableDatabase().query("alarmas",columnas,null,null,null,null,null);

        ItemAlarma[] itemAlarmas = new ItemAlarma[c.getCount()];
        int i = 0;

        int id,es,hor,di,nom,dom,lun,mar,mier,jue,vier,sab;
        id=c.getColumnIndex(_ID);
        es=c.getColumnIndex("habilitado");
        hor=c.getColumnIndex("horario");
        di=c.getColumnIndex("dias");
        nom=c.getColumnIndex("nombre");
        dom=c.getColumnIndex("domingo");
        lun=c.getColumnIndex("lunes");
        mar=c.getColumnIndex("martes");
        mier=c.getColumnIndex("miercoles");
        jue=c.getColumnIndex("jueves");
        vier=c.getColumnIndex("viernes");
        sab=c.getColumnIndex("sabado");

        c.moveToFirst();

        while(!c.isAfterLast()){
            Log.v("mensaje",c.getInt(id) +"");
            boolean valor = c.getInt(es) > 0;
            ItemAlarma itemAlarma = new ItemAlarma(c.getInt(id)
                    ,valor
                    ,c.getString(hor)
                    ,c.getString(di)
                    ,c.getString(nom)
                    ,c.getInt(dom)
                    ,c.getInt(lun)
                    ,c.getInt(mar)
                    ,c.getInt(mier)
                    ,c.getInt(jue)
                    ,c.getInt(vier)
                    ,c.getInt(sab));

            itemAlarmas[i] = itemAlarma;
            i++;
            c.moveToNext();
        }

        return itemAlarmas;

    }

     public void abrir(){
        this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }


}
