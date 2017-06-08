package com.example.owner.gravity2.VerletCore;


import com.example.owner.gravity2.Graphics.IGraphics;
import com.example.owner.gravity2.VerletCore.Constraints.IConstraint;
import com.example.owner.gravity2.VerletCore.Constraints.PinConstraint;

import java.util.ArrayList;

public abstract class Composite {


    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public void setParticles(ArrayList<Particle> particles) {
        this.particles = particles;
    }

    public ArrayList<IConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(ArrayList<IConstraint> constraints) {
        this.constraints = constraints;
    }

    protected ArrayList<Particle> particles = new ArrayList<Particle>();
    protected ArrayList<IConstraint> constraints = new ArrayList<IConstraint>();

    public void draw(IGraphics graphics)
    {
        drawConstraints(graphics);
        drawParticles(graphics);
    }

    public void drawParticles(IGraphics graphics)
    {
        for(IEntity particle:particles) {
            particle.draw(graphics);
        }
    }

    public IEntity pin(int index, Vec2 _pos)
    {
        Vec2 pos = _pos != null ? _pos : particles.get(index).getPos();
        IConstraint pc = new PinConstraint(particles.get(index), pos);
        constraints.add(pc);
        return pc;
    }

    public void unpin(int index, Vec2 _pos)
    {

        for (int i=0; i<constraints.size();i++){
            if (constraints.get(i)!=null){
                constraints.remove(i);
            }
        }

    }

    public void unpin(int index)
    {
       unpin(index, null);
    }

    public IEntity pin(int index)
    {
        return pin(index, null);
    }

    public void drawConstraints(IGraphics graphics)
    {
        for(IEntity constraint:constraints) {
            constraint.draw(graphics);
        }
    }


}