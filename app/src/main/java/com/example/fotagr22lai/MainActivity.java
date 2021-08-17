package com.example.fotagr22lai;

// Code is based on CS349 Repo
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Observer {
    // Private Vars
    public Model model;
    public Widgets toolbar;
    public GridView gv;


    /**
     * OnCreate
     * -- Called when application is initially launched.
     *    @see <a href="https://developer.android.com/guide/components/activities/activity-lifecycle.html">Android LifeCycle</a>
     * @param savedInstanceState
     */
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println("oncreate");
        setContentView(R.layout.activity_main);
        try {
            this.model = new Model(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.model.addObserver(this);
        this.toolbar = new Widgets(this, this.model);
        this.gv = findViewById(R.id.Pictures);
        this.model.gv = findViewById(R.id.Pictures);
        // Adapter
        //Adapter gvAdapter = new ImageAdaptor(this.model, this);
        //this.gv.setAdapter((ListAdapter) gvAdapter);

    }

    @Override
    public void onConfigurationChanged(Configuration newconf) {
        super.onConfigurationChanged(newconf);
        //Log.d("newconf","newconf");
        switch(newconf.orientation) {
            case Configuration.ORIENTATION_PORTRAIT: {
                gv.setNumColumns(1);
                break;
            }
            case Configuration.ORIENTATION_LANDSCAPE: {
                gv.setNumColumns(2);
                break;
            }
        }

    }


    @Override
    public void update(Observable observable, Object o) {
        //Log.d("update","mainac");
        ((ImageAdaptor)gv.getAdapter()).filter();
        ((ImageAdaptor)gv.getAdapter()).notifyDataSetChanged();
    }
}
