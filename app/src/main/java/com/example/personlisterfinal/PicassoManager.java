package com.example.personlisterfinal;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PicassoManager {
    Context context;
    String imgName;
    String imgDir;

    public PicassoManager(Context context, String imgDir, String imgName){
        this.context = context;
        this.imgName = imgName;
        this.imgDir = imgDir;
    }
    public Target picassoImageTarget() {
        Log.d("PicassoManager:", " picassoImageTarget");
        ContextWrapper cw = new ContextWrapper(this.context);
        File imageDir = cw.getDir(imgDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final File myImageFile = new File(imageDir, imgName); // Create image file
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(myImageFile);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {

                }
            }
        };
    }
    public File loadImageFile(){
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(imgDir, Context.MODE_PRIVATE);
        File myImageFile = new File(directory, imgName);
        return myImageFile;
    }
}
