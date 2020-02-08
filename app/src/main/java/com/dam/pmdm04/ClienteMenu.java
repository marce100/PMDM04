package com.dam.pmdm04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.dam.pmdm04.menu.ToolBarCliente;

public class ClienteMenu extends ToolBarCliente {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_menu);

        //Set Cabecera
        Cabecera c = new Cabecera(this);
        c.setUpCabecera();

        //Set Toolbar
        setUpToolBar();
    }


    /*
     * Botón Salir
     */
    public void salir(View view) {
        // Finaliza la actividad, también se puede usar onBackPressed()
        finish();
    }


    /*
     * Botón Hacer pedido
     */
    public void hacerPedido(View view){
        //Inicia una nueva actividad
        startActivity(new Intent(this, ClienteHacerPedido.class));
    }


    /*
     * Botón Ver pedidos en trámite
     */
    public void verPedidos(View view){
        //Inicia una nueva actividad
        startActivity(new Intent(this, ClienteVerPedidos.class));
    }


    /*
     * Botón Ver compras realizadas
     */
    public void verCompras(View view){
        //Inicia una nueva actividad
        startActivity(new Intent(this, ClienteVerCompras.class));
    }


    /*
     * Botón Actualizar perfil
     */
    public void actualizarPerfil(View view){
        //Inicia una nueva actividad
        Intent intent = new Intent(this, Registro.class);
        intent.putExtra("nuevoUsuario", "NO");
        startActivity(intent);
    }

}
