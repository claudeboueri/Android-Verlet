package com.example.owner.gravity2.VerletCore;


import com.example.owner.gravity2.Graphics.IGraphics;

public interface IEntity {


        void draw(IGraphics graphics);
        Vec2 getPos();

}
