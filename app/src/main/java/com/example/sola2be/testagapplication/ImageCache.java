package com.example.sola2be.testagapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Sola2Be on 13.04.2016.
 */
public class ImageCache {

    private ImageCache() {
        cache = new HashMap<>();
    }

    private static ImageCache imageCache;
    private HashMap<String, Bitmap> cache;

    public static ImageCache init() {
        if (imageCache == null) {
            imageCache = new ImageCache();
        }
        return imageCache;
    }


    public void loadImage(final ImageView imageView, String urlString) {
        if (cache.get(urlString) != null) {
            imageView.setImageBitmap(cache.get(urlString));
        } else {
            AsyncTask<String, Void, Bitmap> task = new AsyncTask<String, Void, Bitmap>() {
                String urlString;

                @Override
                protected Bitmap doInBackground(String... params) {
                    Bitmap bitmap = null;
                    try {
                        urlString = params[0];
                        URL url = new URL(urlString);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.connect();
                        InputStream is = urlConnection.getInputStream();
                        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inJustDecodeBounds = true;
//                        BitmapFactory.decodeStream(is,null,options);
//                        options.inSampleSize = calculateInSampleSize(options, 300, 300);
//                        options.inJustDecodeBounds = false;
                        bitmap = BitmapFactory.decodeStream(is, null, options);
                        urlConnection.disconnect();
                        is.close();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        Log.e("Error ListAdapter", "MalformedURLException");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("Error ListAdapter", "IOException");
                    }
                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);
                    imageView.setImageBitmap(bitmap);
                    cache.put(urlString, bitmap);
                }
            };
            task.execute(urlString);
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}

