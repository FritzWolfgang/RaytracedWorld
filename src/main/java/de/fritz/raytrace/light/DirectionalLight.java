package de.fritz.raytrace.light;

import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.Scene;

public class DirectionalLight extends Light{


    Vector3 direction;

    public DirectionalLight(Scene scene, Vector3 direction, double intensity) {
        super(scene, Light.DIRECTIONAL, intensity);
        this.direction = direction;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }
}
