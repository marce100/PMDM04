package com.dam.pmdm03.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dam.pmdm03.R;
import com.dam.pmdm03.room.entidades.Pedido;
import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter {

    private ArrayList<Pedido> pedidos;
    private boolean botones;

    public RecycleViewAdapter(ArrayList<Pedido> pedidos, boolean botones) {
        this.pedidos=pedidos;
        this.botones=botones;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = mInflater.inflate(R.layout.card_layout,viewGroup,false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder(v, pedidos, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolderMeu = (ViewHolder) viewHolder;
        viewHolderMeu.itemImaxe1.setTag(pedidos.get(i));
        viewHolderMeu.itemImaxe2.setTag(pedidos.get(i));
        viewHolderMeu.itemTexto1.setText("Usuario: "+pedidos.get(i).getIdUsuario()+" / Pedido:"+pedidos.get(i).getId());
        viewHolderMeu.itemTexto2.setText(pedidos.get(i).toString());
        if (!botones) {
            ((ViewHolder) viewHolder).itemImaxe1.setVisibility(View.INVISIBLE);
            ((ViewHolder) viewHolder).itemImaxe2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }
}
