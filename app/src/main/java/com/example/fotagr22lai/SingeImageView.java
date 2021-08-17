package com.example.fotagr22lai;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RatingBar;

import java.util.Observable;
import java.util.Observer;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class SingeImageView extends LinearLayout implements Observer {
    public Model model;
    public View view;
    public Image img;
    public RatingBar ratingBut;
    public ImageButton clearBut;
    public int rating;




    public SingeImageView(final Context context, final Model model, View view, final Image image) {
        super(context);
        this.model = model;
        this.view = view;
        this.img = image;
        this.ratingBut = this.view.findViewById(R.id.ratingBar);
        this.clearBut = this.view.findViewById(R.id.clear_single_rating);
        this.rating = image.rating;
        this.ratingBut.setRating(rating);
        //Log.d("creating", String.valueOf(ratingBut));


        this.view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog popup = new Dialog(SingeImageView.this.view.getContext());
                popup.setContentView(R.layout.popup);
                popup.getWindow().setLayout(MATCH_PARENT, MATCH_PARENT);
                ImageView iv = popup.findViewById(R.id.popup_img);
                // close the popup
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });
                iv.setImageBitmap(image.bmp);
                // display popup window
                popup.show();
            }
        });

        this.ratingBut.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //Log.d("rating", "rating");
                image.updateRating((int) v);
                ((ImageAdaptor) model.gv.getAdapter()).filter();
                ((ImageAdaptor) model.gv.getAdapter()).notifyDataSetChanged();
            }
        });

        this.clearBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("clear", "clear");
                ratingBut.setRating(0);
                ((ImageAdaptor) model.gv.getAdapter()).filter();
                ((ImageAdaptor) model.gv.getAdapter()).notifyDataSetChanged();
            }
        });


    }

    @Override
    public void update(Observable observable, Object o) {
        ImageView imgview = this.view.findViewById(R.id.single_img);
        imgview.setImageBitmap(img.bmp);
    }

}
