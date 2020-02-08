package com.dam.pmdm04;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import com.dam.pmdm04.camara.Utilidades;
import com.dam.pmdm04.room.config.Configuracion;
import com.dam.pmdm04.room.database.AppDatabase;
import com.dam.pmdm04.room.entidades.Usuario;

import java.util.List;

public class Cabecera {

    Long idUsuario;
    String nombre;
    String apellidos;
    TextView t;

    public Cabecera(Context c) {

        //Mostrar nombre y apellidos en la cabecera
        SharedPreferences pref = c.getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        nombre= pref.getString("nombre", null);
        apellidos= pref.getString("apellidos", null);
        idUsuario = pref.getLong("idUsuario", -1);
        t = (TextView) ((Activity)c).findViewById(R.id.textView3);


        //Busco la imagen en la base de datos
        AppDatabase bd;
        bd = Room.databaseBuilder(c.getApplicationContext(), AppDatabase.class, Configuracion.BD_NAME)
                .allowMainThreadQueries()
                .build();

        List<Usuario> lista = bd.usuarioDao().getUsuarios(idUsuario);
        Usuario u = lista.get(0);
        Utilidades.arrayToImageView(u.getImage(),((ImageView) ((Activity) c).findViewById(R.id.imageView)));
    }

    public void setUpCabecera(){
        t.setText(nombre+" "+apellidos);
    }
}
