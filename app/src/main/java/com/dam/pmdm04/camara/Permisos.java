package com.dam.pmdm04.camara;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.dam.pmdm04.R;

import static android.Manifest.permission.CAMERA;

public class Permisos extends AppCompatActivity {

    final int COD_PERMISOS=1;


    /*
     *  Solicitud de permisos
     */
    public void pedirPermiso(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, CAMERA},
                    COD_PERMISOS);
        }
    }


    /*
     *  Resultado de la solicitud de permisos
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode==COD_PERMISOS) {
            Button btn = findViewById(R.id.btnCargarImg);
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permisos concedidos, habilitamos botón
                btn.setEnabled(true);
                //Toast.makeText(this,"PERMISOS CONCEDIDOS",Toast.LENGTH_SHORT).show();
            } else {
                // Permisos denegados, deshabilitamos botón
                btn.setEnabled(false);
                Toast.makeText(this,"NECESITAS PERMISOS",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
