package com.dam.pmdm03.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dam.pmdm03.room.entidades.Pedido;
import com.dam.pmdm03.room.entidades.Usuario;
import com.dam.pmdm03.room.interfaces.PedidoDao;
import com.dam.pmdm03.room.interfaces.UsuarioDao;

/*
 * Permisos de acceso a las tablas:
 *
 * 1º en entities especificamos a que tablas tiene acceso
 * 2º añadimos los interfaces que tienen metodos de acceso a la base de datos (insertar, borrar, ...)
 */

@Database(entities = {Usuario.class, Pedido.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    @SuppressWarnings("WeakerAccess")
    public abstract UsuarioDao usuarioDao();
    public abstract PedidoDao pedidoDao();

    //Manejador de la base de datos
    private static AppDatabase sInstance;

}
