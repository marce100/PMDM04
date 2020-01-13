package com.dam.pmdm03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.dam.pmdm03.room.config.Configuracion;
import com.dam.pmdm03.room.database.AppDatabase;
import com.dam.pmdm03.room.entidades.Usuario;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /*
     * Botón Login
     */
    public void login(View view) {

        /*
         * Obtenemos el usuario y la contraseña
         */
        EditText editText_usuario = (EditText) findViewById(R.id.editText3);
        EditText editText_password = (EditText) findViewById(R.id.editText);
        String usuario=editText_usuario.getText().toString();
        String password=editText_password.getText().toString();


        //Adaptador base de datos
        AppDatabase bd;
        bd = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Configuracion.BD_NAME)
                .allowMainThreadQueries()
                .build();


        //Buscamos el usuario
        List<Usuario> lista = bd.usuarioDao().getUsuarios(usuario,password);


        if (lista.size()==0){
            //Login ERROR: Lanzamos la actividad MensajeLogin y le pasamos como parametro un mensaje de error
            Intent intent = new Intent(this, MostrarMensajeLogin.class);
            intent.putExtra("Resultado", "¡ Hay un error en el usuario y/o la contraseña !");
            startActivity(intent);
        }else{
            Usuario u=lista.get(0);

            //Preferencias
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putLong("idUsuario" , u.getId());
            editor.putString("nombre" , u.getNombre());
            editor.putString("apellidos" , u.getApellidos());
            editor.commit();

            if (u.getTipo().equals("Usuario")){
                //Login OK: Lanzamos la actividad ClienteMenu
                Intent intent = new Intent(this, ClienteMenu.class);
                startActivity(intent);

            }else if (u.getTipo().equals("Administrador")){
                //Login OK: Lanzamos la actividad AdminMenu
                Intent intent = new Intent(this, AdminMenu.class);
                startActivity(intent);

            }
        }
    }


    /*
     * Botón Registro
     */
    public void registro(View view) {
        //Login OK: Lanzamos la actividad Registro
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

}
