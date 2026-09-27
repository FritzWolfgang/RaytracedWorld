package de.fritz.raytrace.objects;

import de.fritz.raytrace.math.Coord3;

import java.awt.*;

public class Sphere extends Shape {

    int radius;


    public Sphere(Coord3 center, int radius, Color color) {
        super(center, color);
        this.radius = radius;

    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
