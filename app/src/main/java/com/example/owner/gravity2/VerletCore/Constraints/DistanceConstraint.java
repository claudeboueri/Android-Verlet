package com.example.owner.gravity2.VerletCore.Constraints;


import android.graphics.Color;
import android.graphics.Paint;

import com.example.owner.gravity2.Graphics.IGraphics;
import com.example.owner.gravity2.VerletCore.Particle;
import com.example.owner.gravity2.VerletCore.Vec2;

public class DistanceConstraint implements IConstraint {

    public void setDistance(float distance) {
        this.distance = distance;
    }

    private float distance;

    public Particle getA() {
        return a;
    }

    public Particle getB() {
        return b;
    }

    private Particle a;
    private Particle b;
    private float stiffness;
//    private int color = Color.parseColor("#d8dde2");
    Vec2 normal = new Vec2();

    public DistanceConstraint(Particle a, Particle b, float stiffness) {
        this.a = a;
        this.b = b;
        this.stiffness = stiffness;
        this.distance = a.getPos().sub(b.getPos()).length();
    }

    public DistanceConstraint(Particle a, Particle b, float stiffness, float distance) {
        this(a, b, stiffness);
        this.distance = distance;
    }

    @Override
    public void relax(float stepCoef) {
        normal.mutableSet(this.a.pos);
        normal.mutableSub(this.b.pos);
        float m = normal.length2();
        normal.mutableScale(((this.distance * this.distance - m) / m) * this.stiffness * stepCoef);
        this.a.pos.mutableAdd(normal);
        this.b.pos.mutableSub(normal);
    }

    public void draw(IGraphics graphics) {
        graphics.setPenStyle(Paint.Style.STROKE);
        graphics.setStrokeWidth(3);
//        graphics.setColorPen(color);
        graphics.drawLine(this.a.getPos().x, this.a.getPos().y,
                this.b.getPos().x, this.b.getPos().y);
    }

    @Override
    public Vec2 getPos() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public float getDistance() {
        return distance;
    }
}