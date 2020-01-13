package com.dam.pmdm03.menu;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.dam.pmdm03.AdminVerPedidosAceptados;
import com.dam.pmdm03.AdminVerPedidosRechazados;
import com.dam.pmdm03.AdminVerPedidosTramite;
import com.dam.pmdm03.MainActivity;
import com.dam.pmdm03.R;

public class ToolBarAdmin extends AppCompatActivity {

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
        inflater.inflate(R.menu.menu_admin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                //Toast.makeText(getApplicationContext(),"Atrás",Toast.LENGTH_LONG).show();
                break;
            case R.id.ver_pedidos_tramite:
                startActivity(new Intent(this, AdminVerPedidosTramite.class));
                break;
            case R.id.ver_pedidos_aceptados:
                startActivity(new Intent(this, AdminVerPedidosAceptados.class));
                break;
            case R.id.ver_pedidos_rechazados:
                startActivity(new Intent(this, AdminVerPedidosRechazados.class));
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
