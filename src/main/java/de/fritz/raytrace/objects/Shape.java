package de.fritz.raytrace.objects;

import de.fritz.raytrace.math.Coord3;

import java.awt.*;

public abstract class Shape {

    Color color;
    Coord3 center;

    public Shape(Coord3 center, Color color) {
        this.center = center;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Coord3 getCenter() {
        return center;
    }

    public void setCenter(Coord3 center) {
        this.center = center;
    }
}
