package com.example.owner.gravity2.VerletCore.Objects;


import com.example.owner.gravity2.VerletCore.Composite;
import com.example.owner.gravity2.VerletCore.Constraints.DistanceConstraint;
import com.example.owner.gravity2.VerletCore.Particle;
import com.example.owner.gravity2.VerletCore.Vec2;

import java.util.ArrayList;

public class LineSegments extends Composite {

    public LineSegments(ArrayList<Vec2> vertices, float stiffness) {

        int i = 0;
        for(Vec2 v:vertices)
        {
            particles.add(new Particle(v));
            if(i > 0)
                constraints.add(new DistanceConstraint(particles.get(i), particles.get(i-1), stiffness));
            i++;
        }
    }
}