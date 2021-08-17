package com.example.fotagr22lai;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.RatingBar;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import static android.view.View.*;

public class Widgets implements Observer {

    public Context context;
    public Model model;

    public ImageButton loadBut;
    public ImageButton clearpicBut;
    public RatingBar ratingBut;
    public ImageButton ratingclearBut;

    public Widgets(final Context context, final Model model) {
        this.context = context;
        this.model = model;
        //View view = ((Activity) context).findViewById(R.id.toolbar);
        Activity a = (Activity) context;
        this.loadBut = a.findViewById(R.id.LoadPic);
        this.ratingBut = a.findViewById(R.id.RatingBar);
        this.ratingclearBut = a.findViewById(R.id.ClearRating);
        this.clearpicBut = a.findViewById(R.id.ClearPic);

        this.loadBut.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Increment mModel counter
                // Adapter
                //Log.d("123","123");
                if(model.image_arr.size() == 0) {
                    try {
                        model.createimgs();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Adapter gvAdapter = new ImageAdaptor(model, context);
                model.gv.setAdapter((ListAdapter) gvAdapter);
            }
        });


        this.ratingBut.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                model.filter_limit = (int) v;
                Log.d("ratingBut", String.valueOf(model.filter_limit));
                model.notifyObservers();
                Adapter gvAdapter = new ImageAdaptor(model, context);
                model.gv.setAdapter((ListAdapter) gvAdapter);
            }
        });

        this.clearpicBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clearpicBut","123");
                //model.clearImgs();
                //Adapter gvAdapter = new ImageAdaptor(model, context);
                model.clearRating();
                model.gv.setAdapter(null);
            }
        });

        this.ratingclearBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ratingclearBut","123");
                ratingBut.setRating(0);
                Adapter gvAdapter = new ImageAdaptor(model, context);
                model.gv.setAdapter((ListAdapter) gvAdapter);
            }
        });


    }



    @Override
    public void update(Observable observable, Object o) {

    }
}
