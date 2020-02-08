package com.dam.pmdm04;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.dam.pmdm04.camara.Camara;
import com.dam.pmdm04.camara.Utilidades;
import com.dam.pmdm04.room.config.Configuracion;
import com.dam.pmdm04.room.database.AppDatabase;
import com.dam.pmdm04.room.entidades.Usuario;

import java.util.List;
import java.util.regex.Pattern;

public class Registro extends Camara {

    Long idUsuario;
    boolean nuevoUsuario = false;
    String tipoUsuario;


    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        //Comprobamos si es un usuario nuevo
        if (getIntent().getStringExtra("nuevoUsuario").equals("SI"))
           nuevoUsuario = true;
        else
            nuevoUsuario = false;


        //No es un usuario nuevo
        if (!nuevoUsuario) {

            //Recogo el id de usuario del archivo de preferencias
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            idUsuario = pref.getLong("idUsuario", -1);
            tipoUsuario = pref.getString("tipo", null);

            //Busco el usuario en la base de datos
            AppDatabase bd;
            bd = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Configuracion.BD_NAME)
                    .allowMainThreadQueries()
                    .build();

            List<Usuario> lista = bd.usuarioDao().getUsuarios(idUsuario);
            Usuario u = lista.get(0);

            //Relleno el formulario con los datos del usuario
            ((EditText) findViewById(R.id.editText6)).setText(u.getNombre());
            ((EditText) findViewById(R.id.editText4)).setText(u.getApellidos());
            ((EditText) findViewById(R.id.editText2)).setText(u.getEmail());
            ((EditText) findViewById(R.id.editText7)).setText(u.getUsuario());
            ((EditText) findViewById(R.id.editText8)).setText(u.getPassword());
            if (u.getTipo().equals("Administrador"))
                ((RadioButton) findViewById(R.id.radioButton4)).setChecked(true);
            Utilidades.arrayToImageView(u.getImage(),((ImageView) findViewById(R.id.imagemId)));

            //Marco como visible el campo para pedir la contraseña por segunda vez
            ((TextView) findViewById(R.id.textView11)).setVisibility(View.VISIBLE);
            ((EditText) findViewById(R.id.editText11)).setVisibility(View.VISIBLE);

            //Deshabilito del campo usuario
            ((EditText) findViewById(R.id.editText7)).setEnabled(false);

            //Cambio el titulo de la ventana
            ((TextView) findViewById(R.id.textView5)).setText("Perfil de usuario");
            if (tipoUsuario.equals("Administrador")){
                ((TextView) findViewById(R.id.textView5)).setBackground(getResources().getDrawable(R.drawable.bordes_rojo));
                ((TextView) findViewById(R.id.textView5)).setTextColor(Color.parseColor("#D81B60"));
            }

        }


        //Pedir permisos para utilizar la camara y la tarjeta SD
        pedirPermiso();
    }



    /*
     * Botón Sacar/Cargar Fotografía
     */
    public void cargarImagen(View view) {
        final CharSequence[] opciones={"Tomar Foto","Cargar Imagen","Cancelar"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(Registro.this);
        alertOpciones.setTitle("Seleccione una opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    tomarFotografia();
                }else{
                    if (opciones[i].equals("Cargar Imagen")){
                        cargarFotografia();
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertOpciones.show();
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
        EditText editText_repite_password = (EditText) findViewById(R.id.editText11);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton_tipoUsuario = (RadioButton) findViewById(selectedId);

        ImageView imagen= (ImageView) findViewById(R.id.imagemId);


        String nombre=editText_nombre.getText().toString();
        String apellidos=editText_apellidos.getText().toString();
        String email=editText_email.getText().toString();
        String usuario=editText_usuario.getText().toString();
        String password=editText_password.getText().toString();
        String repite_password=editText_repite_password.getText().toString();
        String tipoUsuario=radioButton_tipoUsuario.getText().toString();

        byte[] imageInByte=Utilidades.imageViewToArray(imagen);


        //Validar datos
        String errores="";
        editText_nombre.setBackgroundColor(Color.TRANSPARENT);
        editText_apellidos.setBackgroundColor(Color.TRANSPARENT);
        editText_usuario.setBackgroundColor(Color.TRANSPARENT);
        editText_password.setBackgroundColor(Color.TRANSPARENT);
        editText_email.setBackgroundColor(Color.TRANSPARENT);
        editText_repite_password.setBackgroundColor(Color.TRANSPARENT);

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
        //No es un usuario nuevo (validamos también el campo repetir_password)
        if (!nuevoUsuario) {
            if (repite_password.isEmpty()) {
                editText_repite_password.setBackgroundColor(Color.RED);
                errores+="Repite la contraseña.\n";
            }
            if (!(password.equals(repite_password))){
                editText_password.setBackgroundColor(Color.RED);
                editText_repite_password.setBackgroundColor(Color.RED);
                errores+="Las contraseñas no son iguales.\n";
            }
        }

        if (!errores.equals(""))
            Toast.makeText(getApplicationContext(), errores, Toast.LENGTH_LONG).show();


        //Comprobar si ya existe el usuario
        if (nuevoUsuario) {
            if (errores.equals("")) {
                int existeUsuario = bd.usuarioDao().existeUsuario(usuario);
                if (existeUsuario > 0) {
                    errores = "El usuario " + usuario + " ya está registrado.";
                    Toast.makeText(getApplicationContext(), errores, Toast.LENGTH_LONG).show();
                }
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
            u.setImage(imageInByte);

            //Es un usuario nuevo, se hace un Insert en la BD
            if (nuevoUsuario) {
                long resultado = bd.usuarioDao().insertar(u);
                if (resultado > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha creado el usuario", Toast.LENGTH_LONG).show();
                    // Finaliza la actividad
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }

            //No es un usuario nuevo, se hace un Update en la BD
            if (!nuevoUsuario){
                long resultado = bd.usuarioDao().actualizar(idUsuario,u.getNombre(),u.getApellidos(),u.getEmail(),u.getPassword(),u.getTipo(),u.getImage());
                if (resultado > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha actualizado el perfil", Toast.LENGTH_LONG).show();
                    // Sale de la aplicación
                    // Se usa FLAG_ACTIVITY_CLEAR_TOP para que "mate" las actividades que tiene por encima
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }

        }

    }


}
