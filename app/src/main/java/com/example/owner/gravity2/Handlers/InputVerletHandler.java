package com.example.owner.gravity2.Handlers;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.owner.gravity2.VerletCore.Verlet;

public class InputVerletHandler implements View.OnTouchListener {
    private Verlet verlet;
    private final float scaleX;
    private final float scaleY;

    public InputVerletHandler(Verlet sim, float scaleX, float scaleY) {

        this.verlet = sim;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            verlet.onTouchDown(event.getX()*scaleX, event.getY()*scaleY, 0);
            Log.v("Touch", String.format("ACTION_DOWN x:%.2f y:%.2f", event.getX(), event.getY()));
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            verlet.onTouchMove(event.getX()*scaleX, event.getY()*scaleY, 0);
            Log.v("Touch","ACTION_MOVE");
        }
        else if(event.getAction() == MotionEvent.ACTION_UP)
        {
            verlet.onTouchUp(event.getX()*scaleX, event.getY()*scaleY, 0);
            Log.v("Touch","ACTION_UP");
        }
        return true;
    }
}
