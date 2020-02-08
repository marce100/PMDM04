package com.dam.pmdm04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MostrarMensajeLogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_mensaje_login);

        //Recoge el Intent que inició esta actividad y extrae la variable que se le pasó como parametro
        Intent intent = getIntent();
        String message = intent.getStringExtra("Resultado");

        // Asigna el valor de la variable a un textView para que se vea por pantalla
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}
