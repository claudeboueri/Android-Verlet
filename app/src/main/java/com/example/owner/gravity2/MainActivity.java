package com.example.owner.gravity2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.support.v4.app.Fragment;


import com.example.owner.gravity2.Components.IUpdatable;
import com.example.owner.gravity2.Components.RenderView;
import com.example.owner.gravity2.Graphics.Graphics;
import com.example.owner.gravity2.Graphics.IGraphics;
import com.example.owner.gravity2.Handlers.AccelerometerVerletHandler;
import com.example.owner.gravity2.Handlers.MultyTouchVerletHandler;
import com.example.owner.gravity2.VerletCore.Objects.Cloth;
import com.example.owner.gravity2.VerletCore.Objects.LineSegments;
import com.example.owner.gravity2.VerletCore.Particle;
import com.example.owner.gravity2.VerletCore.Vec2;
import com.example.owner.gravity2.VerletCore.Verlet;
import com.github.pwittchen.swipe.library.Swipe;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity
        implements LoaderPage.OnFragmentInteractionListener, IUpdatable {

    private int BACKGRAOUND_COLOR=1;
    private int VERLET_STEP = 16;

    private Swipe swipe;

    RenderView renderView;
    private IGraphics graphics;
    private Particle particle;
    private int frameBufferWidth;
    private int frameBufferHeight;
    private Verlet sim;
    private float scaleX;
    private float scaleY;
    private  Cloth cloth;

    float x1,x2;
    float y1, y2;

    private FragmentActivity myContext;

    private AccelerometerVerletHandler accHandler;
    private PowerManager.WakeLock wakeLock;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        final ProgressDialog progress = new ProgressDialog(this);
        progress.show();

        final FragmentManager fManager =getSupportFragmentManager();

        final LoaderPage firstFragment = new LoaderPage();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
        ft.add(R.id.fragment_place, firstFragment);

        ft.commit();

         new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                progress.dismiss();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                    }
                });
                fManager.beginTransaction().remove(firstFragment).commit();
            }
        }, 6000);

    }


    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


           /*final ProgressDialog progress = new ProgressDialog(this);
        progress.show();*/

/*
        final FragmentManager fManager =getSupportFragmentManager();

        final LoaderPage firstFragment = new LoaderPage();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
        ft.add(R.id.fragment, firstFragment);
        ft.addToBackStack(null);
*/

//       ft.commit();



}