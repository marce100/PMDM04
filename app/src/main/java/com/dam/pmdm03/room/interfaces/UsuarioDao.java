package com.dam.pmdm03.room.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.dam.pmdm03.room.entidades.Usuario;
import java.util.List;

/*
 * Metodos de acceso a la base de datos
 */

@Dao
public interface UsuarioDao {

    //Registrar un usuario
    @Insert
    long insertar(Usuario usuarios);

    //Comprobar si existe un usuario
    @Query("SELECT COUNT(*) FROM USUARIO WHERE usuario = :usuario")
    int existeUsuario(String usuario);

    //Login
    @Query("SELECT * FROM  USUARIO WHERE usuario = :usuario AND password = :password")
    List<Usuario> getUsuarios(String usuario, String password);

}
