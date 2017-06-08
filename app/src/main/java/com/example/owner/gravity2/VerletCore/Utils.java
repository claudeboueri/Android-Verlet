package com.example.owner.gravity2.VerletCore;



public class Utils {


    public static float lerp(float a, float b, float u) {
        return (1 - u) * a + u * b;

    }
}
