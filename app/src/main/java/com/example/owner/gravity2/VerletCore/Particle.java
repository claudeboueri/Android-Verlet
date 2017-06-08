package com.example.owner.gravity2.VerletCore;


import android.graphics.Color;
import android.graphics.Paint;

import com.example.owner.gravity2.Graphics.IGraphics;

public class Particle implements IEntity {
    int color = Color.parseColor("#000000");

    public Vec2 getPos() {
        return pos;
    }

    public void setPos(Vec2 pos) {
        this.pos = pos;
    }


    public Vec2 pos;

    public Vec2 getLastPos() {
        return lastPos;
    }

    public void setLastPos(Vec2 lastPos) {
        this.lastPos = lastPos;
    }

    public Vec2 lastPos;

    public Particle(Vec2 pos) {
        this.pos = (new Vec2()).mutableSet(pos);
        this.lastPos = (new Vec2()).mutableSet(pos);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void draw(IGraphics graphics) {
        graphics.setColorPen(color);
        graphics.setPenStyle(Paint.Style.FILL);
        graphics.drawCircle(pos.x, pos.y, 8);
        // graphics.drawArc((float)this.pos.getX(), (float)this.pos.getY(), 5.0, 0, 360.0, Color.parseColor("#2dad8f"));
    }
}