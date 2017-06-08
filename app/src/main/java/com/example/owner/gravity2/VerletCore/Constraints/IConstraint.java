package com.example.owner.gravity2.VerletCore.Constraints;

import com.example.owner.gravity2.VerletCore.IEntity;



public interface IConstraint extends IEntity {
    void relax(float stepCoef);
}