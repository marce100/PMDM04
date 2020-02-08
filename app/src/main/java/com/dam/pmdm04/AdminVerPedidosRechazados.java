package com.dam.pmdm04;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.dam.pmdm04.adaptadores.RecycleViewAdapter;
import com.dam.pmdm04.menu.ToolBarAdmin;
import com.dam.pmdm04.room.config.Configuracion;
import com.dam.pmdm04.room.database.AppDatabase;
import com.dam.pmdm04.room.entidades.Pedido;
import java.util.ArrayList;

public class AdminVerPedidosRechazados extends ToolBarAdmin {

    private RecycleViewAdapter recycleAdapter;
    private ArrayList<Pedido> pedidos;

    private void inicializarDatosPedidos(){

        //Adaptador base de datos
        AppDatabase bd;
        bd = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Configuracion.BD_NAME)
                .allowMainThreadQueries()
                .build();

        //Buscamos los pedidos en trámite
        pedidos = new ArrayList<Pedido>(bd.pedidoDao().getPedidos("rechazado"));

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
        setContentView(R.layout.activity_admin_ver_pedidos_rechazados);

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
