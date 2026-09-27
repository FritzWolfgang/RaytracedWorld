package de.fritz.raytrace.light;

import de.fritz.raytrace.math.Vector3;

public class DirectionalLight extends Light{


    Vector3 direction;

    public DirectionalLight(Vector3 direction, double intensity) {
        super(Light.DIRECTIONAL, intensity);
        this.direction = direction;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }
}
