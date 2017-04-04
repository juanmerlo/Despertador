package com.juanmartin.despertador;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by juanmartin on 13/6/2016.
 */
public class ItemAlarmaAdapter extends ArrayAdapter<ItemAlarma> {

    Context myContext;
    int mylayoutResourceID;
    ItemAlarma mydata[] = null;

    public ItemAlarmaAdapter(Context context, int layoutResourceID, ItemAlarma[] data){

        super(context,layoutResourceID,data);
        this.myContext = context;
        this.mylayoutResourceID = layoutResourceID;
        this.mydata = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ItemAlarmaHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)myContext).getLayoutInflater();
            row = inflater.inflate(mylayoutResourceID,parent,false);
            holder = new ItemAlarmaHolder();
            holder.checkBox = (CheckBox) row.findViewById(R.id.checkbox);
            holder.horario = (TextView) row.findViewById(R.id.horarioView);
            holder.dias = (TextView) row.findViewById(R.id.diasView);
            holder.nombre = (TextView) row.findViewById(R.id.nombreView);
            row.setTag(holder);
        }else{

            holder = (ItemAlarmaHolder)row.getTag();
        }

        ItemAlarma itemAlarma = mydata[position];
        holder.id = itemAlarma.id;
        holder.horario.setText(itemAlarma.horario);
        //holder.dias.setText(itemAlarma.dias);


        holder.dias.setText(dameDiasEnTexto(holder , itemAlarma));

        holder.nombre.setText(itemAlarma.nombre);
        holder.checkBox.setChecked(itemAlarma.estado);
        holder.d = itemAlarma.d;
        holder.l = itemAlarma.l;
        holder.m = itemAlarma.m;
        holder.mi = itemAlarma.mi;
        holder.j = itemAlarma.j;
        holder.vi = itemAlarma.vi;
        holder.s = itemAlarma.s;

        return row;
    }

    private String dameDiasEnTexto(ItemAlarmaHolder holder, ItemAlarma itemAlarma){

        boolean primero = true;
        String texto = "";

        if(itemAlarma.d==1){
            texto = "Dom";
            primero = false;
        }

        if(itemAlarma.l ==1){
            if(primero){
                texto = "Lun";
                primero = false;
            }else {
                texto = texto + ", Lun";
            }
        }

        if(itemAlarma.m ==1){
            if(primero){
                texto = "Mar";
                primero = false;
            }else {
                texto = texto + ", Mar";
            }
        }

        if(itemAlarma.mi ==1){
            if(primero){
                texto = "Mier";
                primero = false;
            }else {
                texto = texto + ", Mier";
            }
        }

        if(itemAlarma.j ==1){
            if(primero){
                texto = "Jue";
                primero = false;
            }else {
                texto = texto + ", Jue";
            }
        }

        if(itemAlarma.vi ==1){
            if(primero){
                texto = "Vie";
                primero = false;
            }else {
                texto = texto + ", Vie";
            }
        }

        if(itemAlarma.s ==1){
            if(primero){
                texto = "Sab";
                primero = false;
            }else {
                texto = texto + ", Sab";
            }
        }

        return texto;
    }

    static class ItemAlarmaHolder{

        int id;
        int d,l,m,mi,j,vi,s;
        CheckBox checkBox;
        TextView horario;
        TextView dias;
        TextView nombre;

    }


}
