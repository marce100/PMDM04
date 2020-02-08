package com.dam.pmdm04.camara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;
import androidx.core.content.FileProvider;
import com.dam.pmdm04.R;
import java.io.File;

public class Camara extends Permisos {

    final String RUTA_IMAGEN="misFotos";
    final String NOMBRE_IMAGEN="temporal.jpg";
    String path;

    final int COD_SELECCIONA=10;
    final int COD_CAPTURA=11;


    /*
     *  Cargar fotografía de la SD
     */
    public void cargarFotografia(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent, COD_SELECCIONA);
    }


    /*
     *  Sacar fotografía con la cámara y guardar en un archivo temporal
     */
    public void tomarFotografia() {

        File fileImagen=new File(this.getExternalFilesDir(null),RUTA_IMAGEN);
        path=this.getExternalFilesDir(null)+
                File.separator+RUTA_IMAGEN+
                File.separator+NOMBRE_IMAGEN;

        if(fileImagen.exists()==false)
            fileImagen.mkdirs();

        File imagen=new File(path);

        Intent intent=null;
        intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            String authorities=getApplicationContext().getPackageName()+".provider";
            Uri imageUri= FileProvider.getUriForFile(this,authorities,imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else{
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }
        startActivityForResult(intent,COD_CAPTURA);
    }


    /*
     *  Resultado a la solicitud de imágen
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            ImageView imagen= (ImageView) findViewById(R.id.imagemId);

            switch (requestCode){
                case COD_SELECCIONA:
                    Uri miPath=data.getData();
                    imagen.setImageURI(miPath);
                    break;
                case COD_CAPTURA:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    //Log.i("Ruta de almacenamiento","path: "+path);
                                }
                            });
                    Bitmap bitmap= BitmapFactory.decodeFile(path);
                    imagen.setImageBitmap(bitmap);
                    break;
            }
        }
    }

}
