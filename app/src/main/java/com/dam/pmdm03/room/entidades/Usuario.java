package com.dam.pmdm03.room.entidades;

import android.content.ContentValues;
import android.provider.BaseColumns;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/*
 * Tabla de la base de datos
 */

@Entity(tableName = Usuario.TABLE_NAME)
public class Usuario {


    //Variables comunes a todas las entidades
    public static final String TABLE_NAME="usuario";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_ID= BaseColumns._ID;


    //Variables de la entidad Usuario
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "usuario")
    private String usuario;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "tipo")
    private String tipo;


    //Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    //Constructores
    @Ignore
    public Usuario(){

    }

    public Usuario(long id, String nombre, String apellidos, String email, String usuario, String password, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
    }

    public static Usuario fromContentValues(ContentValues values){
        final Usuario obj = new Usuario();
        if (values.containsKey(COLUMN_ID)){
            obj.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_NAME)){
            obj.setId(1);
        }
        return obj;
    }


}
