package com.dam.pmdm03.menu;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.dam.pmdm03.ClienteHacerPedido;
import com.dam.pmdm03.ClienteVerCompras;
import com.dam.pmdm03.ClienteVerPedidos;
import com.dam.pmdm03.MainActivity;
import com.dam.pmdm03.R;

public class ToolBarCliente extends AppCompatActivity {

    public Toolbar toolbar;

    public void setUpToolBar() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon();
    }

    public void showHomeUpIcon() {
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cliente,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                //Toast.makeText(getApplicationContext(),"Atrás",Toast.LENGTH_LONG).show();
                break;
            case R.id.hacer_pedido:
                startActivity(new Intent(this, ClienteHacerPedido.class));
                break;
            case R.id.ver_pedidos:
                startActivity(new Intent(this, ClienteVerPedidos.class));
                break;
            case R.id.ver_compras:
                startActivity(new Intent(this, ClienteVerCompras.class));
                break;
            case R.id.salir:
                //Se usa FLAG_ACTIVITY_CLEAR_TOP para que "mate" las actividades que tiene por encima
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                //Error desconocido
        }
        return super.onOptionsItemSelected(item);
    }

}
