package com.example.fotagr22lai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Image {
    public Model model;
    public Context context;
    public int rating;
    public String url;
    public Bitmap bmp;


    public Image(Model model, Context context, String url) throws IOException {
        this.model = model;
        this.context = context;
        this.url = url;
        this.rating = 0;
        new DownloadImg(this).execute(this.url);
    }

    public void updateRating(int newrating) {
        this.rating = newrating;
    }

    private class DownloadImg extends AsyncTask<String, Void, Bitmap> {
        Image img;
        public DownloadImg(Image img) {
            this.img = img;
        }
        protected Bitmap doInBackground(String... urls) {
            Bitmap bmp = null;
            String url = urls[0];
            try {
                InputStream in = new java.net.URL(url).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            img.bmp = result;
        }
    }


}
