package com.dam.pmdm04;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;
import com.dam.pmdm04.menu.ToolBarCliente;
import com.dam.pmdm04.room.config.Configuracion;
import com.dam.pmdm04.room.database.AppDatabase;
import com.dam.pmdm04.room.entidades.Pedido;

public class ClienteFinalizarPedido extends ToolBarCliente {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_finalizar_pedido);

        Cabecera c = new Cabecera(this);
        c.setUpCabecera();

        setUpToolBar();

    }


    /*
     * Botón Finalizar pedido
     */
    public void finalizar(View view) {

        //Recoge el Intent que inició esta actividad y extrae las variables que se le pasaron
        final String categoria    = getIntent().getStringExtra("categoria");
        final String producto     = getIntent().getStringExtra("producto");
        final int cantidad        = Integer.valueOf(getIntent().getStringExtra("cantidad"));

        //Recoge las variables de los editText
        EditText editText_direccion =    (EditText) findViewById(R.id.editText5);
        EditText editText_ciudad =       (EditText) findViewById(R.id.editText6);
        EditText editText_codigoPostal = (EditText) findViewById(R.id.editText4);

        final String direccion    = editText_direccion.getText().toString();
        final String ciudad       = editText_ciudad.getText().toString();
        final String codigoPostal = editText_codigoPostal.getText().toString();


        //Validar datos
        String errores="";
        editText_direccion.setBackgroundColor(Color.TRANSPARENT);
        editText_ciudad.setBackgroundColor(Color.TRANSPARENT);
        editText_codigoPostal.setBackgroundColor(Color.TRANSPARENT);

        if (direccion.isEmpty()) {
            editText_direccion.setBackgroundColor(Color.RED);
            errores+="La dirección no puede ir en blanco.\n";
        }
        if (ciudad.isEmpty()) {
            editText_ciudad.setBackgroundColor(Color.RED);
            errores+="La ciudad no puede ir en blanco.\n";
        }
        if (codigoPostal.isEmpty()) {
            editText_codigoPostal.setBackgroundColor(Color.RED);
            errores+="El código postal no puede ir en blanco.\n";
        }

        if (!errores.equals("")) {
            Toast.makeText(getApplicationContext(), errores, Toast.LENGTH_LONG).show();
        }else {


            //Mostrar un Dialogo con el resumen de la compra
            CharSequence text = "Resumen\n" +
                    "-------------------------------------\n" +
                    "Categoría: " + categoria + "\n" +
                    "Producto: " + producto + "\n" +
                    "Cantidad: " + cantidad + "\n" +
                    "Dirección: " + direccion + "\n" +
                    "Ciudad: " + ciudad + "\n" +
                    "Codigo postal: " + codigoPostal + "\n" +
                    "-------------------------------------\n" +
                    "¿ Desea confirmar la compra ?";
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmación");
            builder.setMessage(text)
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Adaptador base de datos
                            AppDatabase bd;
                            bd = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Configuracion.BD_NAME)
                                    .allowMainThreadQueries()
                                    .build();
                            //Guardar pedido
                            Pedido p = new Pedido();
                            p.setCantidad(cantidad);
                            p.setCategoria(categoria);
                            p.setCiudad(ciudad);
                            p.setCodigoPostal(Integer.valueOf(codigoPostal));
                            p.setDireccion(direccion);
                            p.setEstado("en trámite");
                            p.setProducto(producto);

                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                            SharedPreferences.Editor editor = pref.edit();
                            p.setIdUsuario(pref.getLong("idUsuario",0));

                            long resultado = bd.pedidoDao().insertar(p);
                            if (resultado > 0) {
                                Toast.makeText(getApplicationContext(), "Compra confirmada...", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                            }
                            salir();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Cancelando compra...", Toast.LENGTH_LONG).show();
                            //dialog.dismiss();
                            salir();
                        }
                    }).show();
        }

    }

    /*
     * Volver a la pantalla de inicio del cliente
     */
    public void salir(){
        //Se usa FLAG_ACTIVITY_CLEAR_TOP para que "mate" las actividades que tiene por encima
        Intent intent = new Intent(this, ClienteMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
