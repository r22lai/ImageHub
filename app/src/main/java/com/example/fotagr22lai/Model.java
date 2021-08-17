package com.example.fotagr22lai;
import android.content.Context;
import android.util.Log;
import android.widget.GridView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// Code is from CS349 Repo mvc2
/**
 * MVC2 Model
 * <p>
 * Created by J. J. Hartmann on 11/19/2017.
 * Email: j3hartma@uwaterloo.ca
 * Copyright 2017
 */


public class Model extends Observable {
    public Context context;
    public ArrayList<Image> image_arr = new ArrayList<>();
    public ArrayList<String> url_string_arr = new ArrayList<>();
    public GridView gv;
    public int filter_limit;

    public Model(Context context) throws IOException {
        this.context = context;
        createimgs();
    }

    public void createimgs() throws IOException {

        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/bunny.jpg");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/chinchilla.jpg");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/doggo.jpg");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/fox.jpg");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/hamster.jpg");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/husky.jpg");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/kitten.png");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/loris.jpg");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/puppy.jpg");
        url_string_arr.add("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/sleepy.png");
        Image img0 = new Image(this, this.context, url_string_arr.get(0));
        Image img1 = new Image(this, this.context, url_string_arr.get(1));
        Image img2 = new Image(this, this.context, url_string_arr.get(2));
        Image img3 = new Image(this, this.context, url_string_arr.get(3));
        Image img4 = new Image(this, this.context, url_string_arr.get(4));
        Image img5 = new Image(this, this.context, url_string_arr.get(5));
        Image img6 = new Image(this, this.context, url_string_arr.get(6));
        Image img7 = new Image(this, this.context, url_string_arr.get(7));
        Image img8 = new Image(this, this.context, url_string_arr.get(8));
        Image img9 = new Image(this, this.context, url_string_arr.get(9));

        image_arr.add(img0);
        image_arr.add(img1);
        image_arr.add(img2);
        image_arr.add(img3);
        image_arr.add(img4);
        image_arr.add(img5);
        image_arr.add(img6);
        image_arr.add(img7);
        image_arr.add(img8);
        image_arr.add(img9);

    }

    public void clearImgs() {
        this.image_arr.clear();
        this.notifyObservers();
    }

    public void clearRating() {
        for(Image img: image_arr) {
            img.updateRating(0);
        }
    }

}
