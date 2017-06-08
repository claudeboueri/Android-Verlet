package com.example.owner.gravity2.VerletCore.Constraints;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.owner.gravity2.Graphics.IGraphics;
import com.example.owner.gravity2.VerletCore.IEntity;
import com.example.owner.gravity2.VerletCore.Vec2;


public class PinConstraint implements IConstraint {

    private final IEntity a;
    private final Vec2 pos;
    int color = Color.argb(25, 0, 153, 255);
    public PinConstraint(IEntity a, Vec2 pos) {
        this.a = a;
        this.pos = (new Vec2()).mutableSet(pos);
    }


    public void relax(float stepCoef)
    {
        this.a.getPos().mutableSet(this.pos);
    }

    @Override
    public void draw(IGraphics graphics) {
        graphics.setPenStyle(Paint.Style.FILL);
        graphics.setColorPen(color);
        graphics.drawCircle(this.pos.x, this.pos.y, 14);
    }

    @Override
    public Vec2 getPos() {
        return pos;
    }

    public IEntity getA() {
        return a;
    }


}