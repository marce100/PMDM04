package com.dam.pmdm03;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.dam.pmdm03.adaptadores.RecycleViewAdapter;
import com.dam.pmdm03.menu.ToolBarCliente;
import com.dam.pmdm03.room.config.Configuracion;
import com.dam.pmdm03.room.database.AppDatabase;
import com.dam.pmdm03.room.entidades.Pedido;
import java.util.ArrayList;

public class ClienteVerPedidos extends ToolBarCliente {

    private RecycleViewAdapter recycleAdapter;
    private ArrayList<Pedido> pedidos;

    private void inicializarDatosPedidos(){

        //Adaptador base de datos
        AppDatabase bd;
        bd = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Configuracion.BD_NAME)
                .allowMainThreadQueries()
                .build();

        //Buscamos los pedidos en trámite
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        long idUsuario = pref.getLong("idUsuario", 0);
        pedidos = new ArrayList<Pedido>(bd.pedidoDao().getPedidosById(idUsuario,"en trámite"));


    }

    private void inicializarRecycleView(){
        if (pedidos.size()>0) {
            recycleAdapter = new RecycleViewAdapter(pedidos, false);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            RecyclerView recyclerView = findViewById(R.id.rvwRecycleView);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recycleAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_ver_pedidos);

        //Set Cabecera
        Cabecera c = new Cabecera(this);
        c.setUpCabecera();

        //Set Toolbar
        setUpToolBar();

        //Set RecicleView
        inicializarDatosPedidos();
        inicializarRecycleView();
    }
}
