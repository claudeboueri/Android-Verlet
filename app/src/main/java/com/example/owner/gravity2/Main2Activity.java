package com.example.owner.gravity2;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.example.owner.gravity2.Components.IUpdatable;
import com.example.owner.gravity2.Components.RenderView;
import com.example.owner.gravity2.Graphics.Graphics;
import com.example.owner.gravity2.Graphics.IGraphics;
import com.example.owner.gravity2.Handlers.AccelerometerVerletHandler;
import com.example.owner.gravity2.Handlers.MultyTouchVerletHandler;
import com.example.owner.gravity2.VerletCore.Objects.Cloth;
import com.example.owner.gravity2.VerletCore.Particle;
import com.example.owner.gravity2.VerletCore.Vec2;
import com.example.owner.gravity2.VerletCore.Verlet;

import java.util.Timer;
import java.util.TimerTask;


public class Main2Activity extends AppCompatActivity implements  LoaderPage.OnFragmentInteractionListener, IUpdatable{

    private int BACKGRAOUND_COLOR;
    private int VERLET_STEP = 16;

    RenderView renderView;
    private IGraphics graphics;
    private Particle particle;
    private int frameBufferWidth;
    private int frameBufferHeight;
    private Verlet sim;
    private float scaleX;
    private float scaleY;
    private AccelerometerVerletHandler accHandler;
    private PowerManager.WakeLock wakeLock;
    public int Num =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2)*/;

        boolean isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        frameBufferWidth = isLandscape ? 800 :  480;
        frameBufferHeight = isLandscape ? 480 : 800;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Bitmap.Config.RGB_565);

        scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        scaleY = (float) frameBufferHeight
                /  getWindowManager().getDefaultDisplay().getHeight();

        renderView = new RenderView(this, frameBuffer);
        graphics = new Graphics(getAssets(), frameBuffer, this);


        sim = new Verlet(frameBufferWidth, frameBufferHeight, graphics);
        MultyTouchVerletHandler handler = new MultyTouchVerletHandler(sim, scaleX, scaleY);
        accHandler = new AccelerometerVerletHandler(this, sim);
        sim.setFriction(1);
        renderView.setOnTouchListener(handler);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);


        PowerManager powerManager = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Verlet");

        if (Num==1){
            setContentView(renderView);
            loadCloth();

        }



        final FragmentManager fManager =getSupportFragmentManager();

        final LoaderPage firstFragment = new LoaderPage();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
        ft.add(R.id.fragment, firstFragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    private void loadCloth() {
        BACKGRAOUND_COLOR = Color.WHITE;
        VERLET_STEP = 8;
        sim.setFriction(1);
        sim.setHighlightColor(Color.BLUE);


//        int segments = 20;
        final Cloth cloth = new Cloth(new Vec2(frameBufferWidth/2.2, frameBufferHeight/2.1), frameBufferWidth, frameBufferHeight, 20, 6, 2f,this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        cloth.unpin();

                    }
                });
            }
        }, 500);

        sim.getComposites().add(cloth);
    }

    private void RemoveCloth(){

    }



    public void onResume()
    {
        super.onResume();
//        wakeLock.acquire();
        renderView.resume();
    }

    public void onPause()
    {
        super.onPause();
//        wakeLock.release();
        renderView.pause();
    }

    int i = 0;
    float fps = 0;
    float fps2 = 0;
    float result = 0;
    float result2 = 0;
    long startTime;
    float _deltaTime = 0;
    int legIndex = 0;
    @Override
    public void update(float deltaTime) {


        graphics.clear(BACKGRAOUND_COLOR);
        fps += 1.0/deltaTime;
        fps2 += 1.0/_deltaTime;
        i++;

        if(i >= 25)
        {
            result = fps/i;
            result2 = fps2/i;
            fps = 0;
            fps2=0;
            i = 0;
        }
        graphics.setColorPen(Color.BLACK);
        graphics.setPenStyle(Paint.Style.FILL);
        graphics.getPaint().setTextSize(20);
//        graphics.drawText(String.format("FPS: %.2f", result), frameBufferWidth - 95, 30);


        startTime = System.nanoTime();
        sim.frame(VERLET_STEP);
        _deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
        sim.draw();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void SetNum(){
        setContentView(renderView);
        loadCloth();    }
}
