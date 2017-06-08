package com.example.owner.gravity2.VerletCore.Objects;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.owner.gravity2.Graphics.IGraphics;
import com.example.owner.gravity2.R;
import com.example.owner.gravity2.VerletCore.Composite;
import com.example.owner.gravity2.VerletCore.Constraints.DistanceConstraint;
import com.example.owner.gravity2.VerletCore.Constraints.IConstraint;
import com.example.owner.gravity2.VerletCore.Constraints.PinConstraint;
import com.example.owner.gravity2.VerletCore.Particle;
import com.example.owner.gravity2.VerletCore.Utils;
import com.example.owner.gravity2.VerletCore.Vec2;
import com.github.pwittchen.swipe.library.Swipe;

public class Cloth extends Composite {

    Context context;
    private final float min;
    private float width;
    private float height;
    private int segments;
    private float pinMod;
    private int pinConstrainColor = Color.BLUE;
    Vec2[] myPath = {new Vec2(),
            new Vec2(),
            new Vec2(),
            new Vec2(),
            new Vec2()};

//    private Vec2 path5 = new Vec2();

    public Cloth(Vec2 origin, float width, float height, int segments, float pinMod, float stiffness, Context context) {
        this.width = width;
        this.height = height;
        this.segments = segments;
        this.pinMod = pinMod;
        this.min = Math.min(height, width);
        this.context = context;
        float xStride = width / segments;
        float yStride = height / segments;

        Swipe swipe;

        int x, y;
        for (y = 0; y < segments; ++y) {
            for (x = 0; x < segments; ++x) {
                float px = origin.x + x * xStride - width / 2 + xStride / 2;
                float py = origin.y + y * yStride - height / 2 + yStride / 2;
                particles.add(new Particle(new Vec2(px, py)));

                if (x > 0)
                    constraints.add(new DistanceConstraint(particles.get(y * segments + x), particles.get(y * segments + x - 1), stiffness));

                if (y > 0)
                    constraints.add(new DistanceConstraint(particles.get(y * segments + x), particles.get((y - 1) * segments + x), stiffness));
            }
        }

        for (x = 0; x < segments; ++x) {
            if (x % pinMod == 0)
                pin(x);
        }

    }

   public void unpin() {

       int x;
       for (x = 0; x < segments; ++x) {
           if (x % pinMod == 0)
               unpin(x);
       }
   }

//    }
    @Override
    public void drawConstraints(IGraphics graphics) {
        float stride = min / segments;
        int x, y;
        for (y = 1; y < segments; ++y) {
            for (x = 1; x < segments; ++x) {
                int i1 = (y - 1) * segments + x - 1;
                int i2 = (y) * segments + x;
                float off = particles.get(i2).pos.x - particles.get(i1).pos.x;
                off += particles.get(i2).pos.y - particles.get(i1).pos.y;
                off *= 0.25;

                float coef = Math.round((Math.abs(off) / stride) * 255);
                if (coef > 255)
                    coef = 255;


//                graphics.setColorPen(Color.argb((int) (Utils.lerp(0.25f, 1, coef / 255) * 255), (int) coef, 0, (int) (255 - coef)));

//                graphics.setColorPen(R.color.colorAccent);

//                graphics.drawPixmap();
                graphics.setPenStyle(Paint.Style.FILL);

                myPath[0].mutableSet(particles.get(i1).pos.x, particles.get(i1).pos.y);
                myPath[1].mutableSet(particles.get(i1 + 1).pos.x, particles.get(i1 + 1).pos.y);
                myPath[2].mutableSet(particles.get(i1 + 1).pos.x, particles.get(i1 + 1).pos.y);
                myPath[3].mutableSet(particles.get(i2).pos.x, particles.get(i2).pos.y);
                myPath[4].mutableSet(particles.get(i2 - 1).pos.x, particles.get(i2 - 1).pos.y);

                graphics.drawPath(myPath);
            }
        }


        for (IConstraint constraint : constraints) {
            if (constraint instanceof PinConstraint) {
                graphics.setColorPen(pinConstrainColor);

                graphics.drawCircle(constraint.getPos().x, constraint.getPos().y, 4f);
            }
        }
    }

    @Override
    public void drawParticles(IGraphics graphics) {

    }

    public void setHeight(int height){
        this.height=height;
    }

    public void setWidth(int width){
        this.width=width;
    }


}