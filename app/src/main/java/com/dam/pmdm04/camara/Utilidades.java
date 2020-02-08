package com.dam.pmdm04.camara;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;

public class Utilidades {


    /*
     * Convertir la imagen en array de bytes para guardar en la base de datos
     */
    public static byte[] imageViewToArray(ImageView imagen){
        Bitmap bitmap = ((BitmapDrawable) imagen.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }


    /*
     * Asignar array de bytes a ImageView
     */
    public static void arrayToImageView(byte[] imageInByte2, ImageView imagen){
        Bitmap bmp = BitmapFactory.decodeByteArray(imageInByte2, 0, imageInByte2.length);
        imagen.setImageBitmap(bmp);
    }

}
