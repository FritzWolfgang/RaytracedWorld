package de.fritz.raytrace.objects.shapes;

import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.Scene;

import java.awt.*;

public abstract class Shape {

    Color color;
    Coord3 center;

    public Shape(Scene scene, Coord3 center, Color color) {
        this.center = center;
        this.color = color;
        scene.addShape(this);
    }

    public Color getColor() {
        return color;
    }

    public abstract Vector3 getLightingNormal(Coord3 p);

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
