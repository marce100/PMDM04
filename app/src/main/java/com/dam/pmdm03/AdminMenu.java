package com.dam.pmdm03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.dam.pmdm03.menu.ToolBarAdmin;

public class AdminMenu extends ToolBarAdmin {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        Cabecera c = new Cabecera(this);
        c.setUpCabecera();

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
     * Botón Ver pedidos en trámite
     */
    public void verPedidosTramite(View view){
        //Inicia una nueva actividad
        startActivity(new Intent(this, AdminVerPedidosTramite.class));
    }


    /*
     * Botón Ver pedidos aceptados
     */
    public void verPedidosAceptados(View view){
        //Inicia una nueva actividad
        startActivity(new Intent(this, AdminVerPedidosAceptados.class));
    }


    /*
     * Botón Ver pedidos rechazados
     */
    public void verPedidosRechazados(View view){
        //Inicia una nueva actividad
        startActivity(new Intent(this, AdminVerPedidosRechazados.class));
    }

}



