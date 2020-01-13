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

@Entity(tableName = Pedido.TABLE_NAME)
public class Pedido {


    //Variables comunes a todas las entidades
    public static final String TABLE_NAME="pedido";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_ID= BaseColumns._ID;


    //Variables de la entidad Pedido
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = "categoria")
    private String categoria;

    @ColumnInfo(name = "producto")
    private String producto;

    @ColumnInfo(name = "cantidad")
    private int cantidad;

    @ColumnInfo(name = "direccion")
    private String direccion;

    @ColumnInfo(name = "ciudad")
    private String ciudad;

    @ColumnInfo(name = "codigo_postal")
    private int codigoPostal;

    @ColumnInfo(name = "estado")
    private String estado;

    @ColumnInfo(name = "idUsuario")
    private long idUsuario;


    //Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public long getIdUsuario() { return idUsuario; }

    public void setIdUsuario(long idUsuario) { this.idUsuario = idUsuario; }

    //Constructores
    @Ignore
    public Pedido(){

    }

    public Pedido(long id, String categoria, String producto, int cantidad, String direccion, String ciudad, int codigoPostal, long idUsuario) {
        this.id = id;
        this.categoria = categoria;
        this.producto = producto;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.idUsuario = idUsuario;
    }

    public static Pedido fromContentValues(ContentValues values){
        final Pedido obj = new Pedido();
        if (values.containsKey(COLUMN_ID)){
            obj.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_NAME)){
            obj.setId(1);
        }
        return obj;
    }

    public String toString(){
        return "Categoría: "        +getCategoria()+"\n"+
                "Producto: "        +getProducto()+"\n"+
                "Cantidad: "        +getCantidad()+"\n"+
                "Dirección: "       +getDireccion()+"\n"+
                "Ciudad: "          +getCiudad()+"\n"+
                "Código postal: "   +getCodigoPostal();
    }

}