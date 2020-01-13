package com.dam.pmdm03.adaptadores;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.dam.pmdm03.R;
import com.dam.pmdm03.room.config.Configuracion;
import com.dam.pmdm03.room.database.AppDatabase;
import com.dam.pmdm03.room.entidades.Pedido;
import java.util.ArrayList;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView itemImaxe1,itemImaxe2;
    public TextView itemTexto1,itemTexto2;

    public ViewHolder(@NonNull View itemView, final ArrayList<Pedido> pedidos, final RecycleViewAdapter r) {
        super(itemView);

        itemImaxe1 = itemView.findViewById(R.id.imgImaxe1);
        itemImaxe2 = itemView.findViewById(R.id.imgImaxe2);
        itemTexto1 = itemView.findViewById(R.id.tvTexto1);
        itemTexto2 = itemView.findViewById(R.id.tvTexto2);

        //Evento Aceptar pedido
        itemImaxe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = getAdapterPosition();
                Pedido pedidoSeleccionado = (Pedido) v.getTag();
                //Toast.makeText(v.getContext(),"Pulsado elemento " + posicion + " da lista.\n" + pedidoSeleccionado,Toast.LENGTH_SHORT).show();

                //Adaptador base de datos
                AppDatabase bd;
                bd = Room.databaseBuilder(v.getContext(), AppDatabase.class, Configuracion.BD_NAME)
                        .allowMainThreadQueries()
                        .build();

                int resultado = bd.pedidoDao().updatePedido(pedidoSeleccionado.getId(),"aceptado");
                if (resultado > 0) {
                    Toast.makeText(v.getContext(), "Aceptado", Toast.LENGTH_SHORT).show();
                    pedidos.remove(posicion);
                    r.notifyItemRemoved(posicion);
                } else {
                    Toast.makeText(v.getContext(), "ERROR", Toast.LENGTH_LONG).show();
                }

            }
        });

        //Evento Rechazar pedido
        itemImaxe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = getAdapterPosition();
                Pedido pedidoSeleccionado = (Pedido) v.getTag();
                //Toast.makeText(v.getContext(),"Pulsado elemento " + posicion + " da lista.\n" + pedidoSeleccionado,Toast.LENGTH_SHORT).show();

                //Adaptador base de datos
                AppDatabase bd;
                bd = Room.databaseBuilder(v.getContext(), AppDatabase.class, Configuracion.BD_NAME)
                        .allowMainThreadQueries()
                        .build();

                int resultado = bd.pedidoDao().updatePedido(pedidoSeleccionado.getId(),"rechazado");
                if (resultado > 0) {
                    Toast.makeText(v.getContext(), "Rechazado", Toast.LENGTH_SHORT).show();
                    pedidos.remove(posicion);
                    r.notifyItemRemoved(posicion);
                } else {
                    Toast.makeText(v.getContext(), "ERROR", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}