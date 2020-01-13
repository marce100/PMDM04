package com.dam.pmdm03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.dam.pmdm03.room.config.Configuracion;
import com.dam.pmdm03.room.database.AppDatabase;
import com.dam.pmdm03.room.entidades.Usuario;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }


    /*
     * Botón Registrar Nuevo usuario
     */
    public void registrarUsuario(View view) {

        //Adaptador base de datos
        AppDatabase bd;
        bd = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Configuracion.BD_NAME)
                .allowMainThreadQueries()
                .build();

        //Recoger datos del formulario
        EditText editText_nombre = (EditText) findViewById(R.id.editText6);
        EditText editText_apellidos = (EditText) findViewById(R.id.editText4);
        EditText editText_email = (EditText) findViewById(R.id.editText2);
        EditText editText_usuario = (EditText) findViewById(R.id.editText7);
        EditText editText_password = (EditText) findViewById(R.id.editText8);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton_tipoUsuario = (RadioButton) findViewById(selectedId);

        String nombre=editText_nombre.getText().toString();
        String apellidos=editText_apellidos.getText().toString();
        String email=editText_email.getText().toString();
        String usuario=editText_usuario.getText().toString();
        String password=editText_password.getText().toString();
        String tipoUsuario=radioButton_tipoUsuario.getText().toString();


        //Validar datos
        String errores="";
        editText_nombre.setBackgroundColor(Color.TRANSPARENT);
        editText_apellidos.setBackgroundColor(Color.TRANSPARENT);
        editText_usuario.setBackgroundColor(Color.TRANSPARENT);
        editText_password.setBackgroundColor(Color.TRANSPARENT);
        editText_email.setBackgroundColor(Color.TRANSPARENT);

        if (nombre.isEmpty()) {
            editText_nombre.setBackgroundColor(Color.RED);
            errores+="El nombre no puede ir en blanco.\n";
        }
        if (apellidos.isEmpty()) {
            editText_apellidos.setBackgroundColor(Color.RED);
            errores+="Los apellidos no pueden ir en blanco.\n";
        }
        if (usuario.isEmpty()) {
            editText_usuario.setBackgroundColor(Color.RED);
            errores+="El usuario no puede ir en blanco.\n";
        }
        if (password.isEmpty()) {
            editText_password.setBackgroundColor(Color.RED);
            errores+="La contraseña no puede ir en blanco.\n";
        }
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if (!pattern.matcher(email).matches()) {
            editText_email.setBackgroundColor(Color.RED);
            errores+="El correo no es valido.\n";
        }
        if (!errores.equals(""))
            Toast.makeText(getApplicationContext(), errores, Toast.LENGTH_LONG).show();


        //Comprobar si ya existe el usuario
        if (errores.equals("")) {
            int existeUsuario = bd.usuarioDao().existeUsuario(usuario);
            if (existeUsuario > 0) {
                errores = "El usuario " + usuario + " ya está registrado.";
                Toast.makeText(getApplicationContext(), errores, Toast.LENGTH_LONG).show();
            }
        }


        //Guardar Usuario
        if (errores.equals("")) {
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setApellidos(apellidos);
            u.setEmail(email);
            u.setUsuario(usuario);
            u.setPassword(password);
            u.setTipo(tipoUsuario);

            long resultado = bd.usuarioDao().insertar(u);
            if (resultado > 0) {
                Toast.makeText(getApplicationContext(), "Se ha creado el usuario", Toast.LENGTH_LONG).show();
                // Finaliza la actividad
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        }

    }


}
