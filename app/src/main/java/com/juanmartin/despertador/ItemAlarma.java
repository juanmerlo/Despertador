package com.juanmartin.despertador;


import android.widget.CheckBox;

/**
 * Created by juanmartin on 13/6/2016.
 */
public class ItemAlarma {

    public CheckBox checkBox;
    public int id;
    public Boolean estado;
    public String horario;
    public String dias;
    public String nombre;
    public int d,l,m,mi,j,vi,s = 0;

    public ItemAlarma(){
        super();
    }

    public ItemAlarma(int id,Boolean estado,String horario, String dias, String nombre,int d, int l, int m, int mi, int j, int vi, int s){

        super();
        this.id = id;
        this.estado = estado;
        this.horario = horario;
        this.dias = dias;
        this.nombre = nombre;
        this.d = d;
        this.l = l;
        this.m = m;
        this.mi = mi;
        this.j = j;
        this.vi = vi;
        this.s = s;

    }



}
