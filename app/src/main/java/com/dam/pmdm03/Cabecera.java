package com.dam.pmdm03;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class Cabecera {

    String nombre;
    String apellidos;
    TextView t;

    public Cabecera(Context c) {

        //Mostrar nombre y apellidos en la cabecera
        SharedPreferences pref = c.getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        nombre= pref.getString("nombre", null);
        apellidos= pref.getString("apellidos", null);
        t = (TextView) ((Activity)c).findViewById(R.id.textView3);

    }

    public void setUpCabecera(){
        t.setText(nombre+" "+apellidos);
    }
}
