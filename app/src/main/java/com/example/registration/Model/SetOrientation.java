package com.example.registration.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SetOrientation {

    public static Bitmap getRotateImage(String photoPath, Bitmap bitmap) throws IOException {
        Log.e("rotated", "getRotateImage: " );
        ExifInterface ei = new ExifInterface(photoPath);
        String orientation = ei.getAttribute(ExifInterface.TAG_ORIENTATION);

//        Bitmap rotatedBitmap = null;
        Log.e("Ex orientation", "getRotateImage: "+orientation );
        return bitmap;
    }


    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Log.e("angle", "rotateImage: "+angle );
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
    public static void getlength(File fileDoc) {
        long length = fileDoc.length();
        length = length / 1024;
        Log.e("", " File size : " + length + " KB");
    }

    public static File convertBitmapToFile(Bitmap bitmap, Context context) throws IOException {
        File f = new File(context.getCacheDir(), System.currentTimeMillis()+"");
        f.createNewFile();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
        return f;
    }
}
