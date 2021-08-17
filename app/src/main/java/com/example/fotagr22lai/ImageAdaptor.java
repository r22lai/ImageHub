package com.example.fotagr22lai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageAdaptor extends BaseAdapter {
    public Model model;
    public Context context;
    public ArrayList<Image> images;
    public ArrayList<SingeImageView> imageviews;

    public ImageAdaptor(Model model, Context context) {
        this.model = model;
        this.context = context;
        this.images = model.image_arr;
        this.imageviews = new ArrayList<>();
        this.filter();
    }

    public void filter() {
        Log.d("filter", "begin");
        images = new ArrayList<>();
        int lowerlimit = this.model.filter_limit;
        ArrayList<Image> unfiltered = model.image_arr;
        int size = unfiltered.size();
        Log.d("filter", String.valueOf(lowerlimit));
        for (int i = 0; i < size; ++i) {
            Image cur_img = unfiltered.get(i);
            int single_rating = cur_img.rating;
            Log.d("filter", String.valueOf(single_rating));
            if(single_rating >= lowerlimit || lowerlimit == 0) {
                images.add(cur_img);
            }
        }

    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Image getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        gridView = inflater.inflate(R.layout.single_image, null);

        ImageView imageView = (ImageView) gridView
                .findViewById(R.id.single_img);

        Image curr_img = images.get(i);
        imageView.setImageBitmap(curr_img.bmp);
        View single_view = LayoutInflater.from(context).inflate(R.layout.single_image, viewGroup, false);

        SingeImageView single_imgview = new SingeImageView(this.context, this.model, gridView, curr_img);

       // imgView.update(imgView, imgView);

        ImageView imgview = single_view.findViewById(R.id.single_img);
        imgview.setImageBitmap(curr_img.bmp);
        imageviews.add(single_imgview);
        //new DownloadImageFromInternet(imageView).execute(this.model.url_string_arr.get(i));
        return gridView;
    }

}
