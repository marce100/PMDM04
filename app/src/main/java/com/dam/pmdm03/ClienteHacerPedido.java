package com.dam.pmdm03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.dam.pmdm03.menu.ToolBarCliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteHacerPedido extends ToolBarCliente {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_hacer_pedido);

        //Set Cabecera
        Cabecera c = new Cabecera(this);
        c.setUpCabecera();

        //Set Toolbar
        setUpToolBar();

        gestionarEventosSpinner();  //Listener spinner categorias
        setSpinnerCantidad();       //Cargar spinner cantidad
    }


    /*
     * Listener para controlar los eventos del spinner de categorias
     * Al cambiar una categoria recarga el spinner de productos
     */
    private void gestionarEventosSpinner(){
        Spinner spinCategorias = (Spinner) findViewById(R.id.spinner2);
        spinCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // TODO Auto-generated method stub
                ArrayAdapter<CharSequence> adapter;
                Spinner spinProductos = (Spinner) findViewById(R.id.spinner3);

                //Según sea la categoria seleccionada se asigna al segundo spinner con un array de productos que lee de un fichero xml (resources)
                switch (parent.getItemAtPosition(pos).toString()){
                    case "Informática":
                        adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.spinner_categoria_informatica, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinProductos.setAdapter(adapter);
                        break;
                    case "Electrónica":
                        adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.spinner_categoria_electronica, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinProductos.setAdapter(adapter);
                        break;
                    case "Móviles":
                        adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.spinner_categoria_moviles, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinProductos.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }


    /*
     * Rellena el spinner cantidad con una lista de opciones
     */
    public void setSpinnerCantidad(){
        Spinner spinCantidad = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCantidad.setAdapter(dataAdapter);
    }


    /*
     * Botón Siguiente
     */
    public void siguiente(View view) {

        //Recoge los valores seleccionados en los spinner
        String categoria=((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString();
        String producto=((Spinner) findViewById(R.id.spinner3)).getSelectedItem().toString();
        String cantidad=((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString();

        //Lanza una nueva actividad y le pasa los valores que recogimos
        Intent intent = new Intent(this, ClienteFinalizarPedido.class);
        intent.putExtra("categoria", categoria);
        intent.putExtra("producto", producto);
        intent.putExtra("cantidad", cantidad);
        startActivity(intent);

    }


}
