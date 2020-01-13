package com.dam.pmdm03.room.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.dam.pmdm03.room.entidades.Pedido;
import java.util.List;

/*
 * Metodos de acceso a la base de datos
 */

@Dao
public interface PedidoDao {

    //Registrar un pedido
    @Insert
    long insertar(Pedido pedidos);

    //Seleccionar pedidos
    @Query("SELECT * FROM  PEDIDO WHERE estado = :estado ORDER BY idUsuario,_id")
    List<Pedido> getPedidos(String estado);

    //Seleccionar pedidos por id del usuario
    @Query("SELECT * FROM  PEDIDO WHERE estado = :estado AND idUsuario = :idUsuario ORDER BY idUsuario,_id")
    List<Pedido> getPedidosById(long idUsuario,String estado);

    //Actualizar estado del pedido
    @Query("UPDATE PEDIDO SET estado = :estado WHERE _id = :idPedido")
    int updatePedido(long idPedido, String estado);
}
