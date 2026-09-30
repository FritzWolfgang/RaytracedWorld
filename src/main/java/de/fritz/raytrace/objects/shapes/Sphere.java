package de.fritz.raytrace.objects.shapes;

import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.Scene;

import java.awt.*;

public class Sphere extends Shape {

    double radius;

    boolean pointLight;

    public Sphere(Scene scene, Coord3 center, double radius, Color color) {
        super(scene, center, color);
        this.radius = radius;
        this.pointLight = false;
    }

    public Sphere(Scene scene, Coord3 center, double radius, Color color, boolean pointLight) {
        super(scene, center, color);
        this.radius = radius;
        this.pointLight = pointLight;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Vector3 getLightingNormal(Coord3 p) {
        return Vector3.calculateVec3d(getCenter(), p).normalize();
    }

    public boolean isPointLight() {
        return pointLight;
    }
}
