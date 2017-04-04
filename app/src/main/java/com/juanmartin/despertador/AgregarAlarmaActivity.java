package com.juanmartin.despertador;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.sql.Time;


public class AgregarAlarmaActivity extends AppCompatActivity {

    TimePicker timePicker;
    EditText nombreAlarma;
    Button guardarBoton, cancelarBoton, repetirBoton;
    CheckBox habilitarCheck;
    int d,l,m,mi,j,vi,s = 0;
    TextView diasRepeticonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alarma);

        inicializar();

    }

    private void inicializar(){

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        nombreAlarma = (EditText) findViewById(R.id.nombreAlarma);
        guardarBoton = (Button) findViewById(R.id.guardarBoton);
        cancelarBoton = (Button) findViewById(R.id.cancelarBoton);
        habilitarCheck = (CheckBox) findViewById(R.id.habilitarCheck);
        repetirBoton = (Button) findViewById(R.id.repetirBoton);
        diasRepeticonText = (TextView) findViewById(R.id.diasRepeticionText);
        timePicker.setIs24HourView(true);

        repetirBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(AgregarAlarmaActivity.this);
                dialogo1.setTitle("Seleccione los días");
                LayoutInflater layoutInflater = LayoutInflater.from(AgregarAlarmaActivity.this);
                View diasView = layoutInflater.inflate(R.layout.dias, null);
                dialogo1.setView(diasView);

                final CheckBox dom,lun,mar,mier,juev,vier,sab;
                dom = (CheckBox)diasView.findViewById(R.id.domingoCheck);
                if(d==1)dom.setChecked(true);
                lun = (CheckBox)diasView.findViewById(R.id.lunesCheck);
                mar = (CheckBox)diasView.findViewById(R.id.martesCheck);
                mier = (CheckBox)diasView.findViewById(R.id.miercolesCheck);
                juev = (CheckBox)diasView.findViewById(R.id.juevesCheck);
                vier = (CheckBox)diasView.findViewById(R.id.viernesCheck);
                sab = (CheckBox)diasView.findViewById(R.id.sabadoCheck);

                if(d==1)dom.setChecked(true);
                if(l==1)lun.setChecked(true);
                if(m==1)mar.setChecked(true);
                if(mi==1)mier.setChecked(true);
                if(j==1)juev.setChecked(true);
                if(vi==1)vier.setChecked(true);
                if(s==1)sab.setChecked(true);


                dialogo1.setCancelable(false);


                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                        if(dom.isChecked()) d = 1;
                        if(lun.isChecked()) l = 1;
                        if(mar.isChecked()) m = 1;
                        if(mier.isChecked()) mi = 1;
                        if(juev.isChecked()) j = 1;
                        if(vier.isChecked()) vi = 1;
                        if(sab.isChecked()) s = 1;

                        int[] diasInt = {d,l,m,mi,j,vi,s};

                        diasRepeticonText.setText(dameDiasTexto(diasInt));

                }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();

            }
        });

        guardarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hora;
                int minutos;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hora = timePicker.getHour();
                    minutos = timePicker.getMinute();
                }else{
                    hora = timePicker.getCurrentHour();
                    minutos = timePicker.getCurrentMinute();
                }

                int habilitado = 0;
                if(habilitarCheck.isChecked()){
                    habilitado = 1;
                }

                String tiempo = hora +":"+minutos;

                String nombre = "Alarma";

                if(!nombreAlarma.getText().toString().equals("")){
                    nombre = nombreAlarma.getText().toString();
                }

                Handler_sqlite helper = new Handler_sqlite(getApplicationContext());
                helper.abrir();
                helper.insertarRegistro(tiempo, "Sab,Mar",nombre,hora,minutos,habilitado,d,l,m,mi,j,vi,s);
                helper.close();
                Intent intent = new Intent();
                intent.putExtra("valor", "ok");
                setResult(RESULT_OK, intent);

                Toast.makeText(getApplicationContext(),"Alarma agregada",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancelarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

    private String dameDiasTexto(int[] diasInt){

        boolean primero = true;
        String texto = "Nunca";

        if(diasInt[0]==1){
            texto = "Dom";
            primero = false;
        }

        if(diasInt[1] ==1){
            if(primero){
                texto = "Lun";
                primero = false;
            }else {
                texto = texto + ", Lun";
            }
        }

        if(diasInt[2] ==1){
            if(primero){
                texto = "Mar";
                primero = false;
            }else {
                texto = texto + ", Mar";
            }
        }

        if(diasInt[3] ==1){
            if(primero){
                texto = "Mier";
                primero = false;
            }else {
                texto = texto + ", Mier";
            }
        }

        if(diasInt[4] ==1){
            if(primero){
                texto = "Jue";
                primero = false;
            }else {
                texto = texto + ", Jue";
            }
        }

        if(diasInt[5] ==1){
            if(primero){
                texto = "Vie";
                primero = false;
            }else {
                texto = texto + ", Vie";
            }
        }

        if(diasInt[6] ==1){
            if(primero){
                texto = "Sab";
                primero = false;
            }else {
                texto = texto + ", Sab";
            }
        }

        return texto;
    }

}
