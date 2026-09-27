package de.fritz.raytrace.light;

import de.fritz.raytrace.math.Coord3;

public class PointLight extends Light{

    Coord3 position;

    public PointLight(Coord3 position, double intensity) {
        super(Light.POINT, intensity);
        this.position = position;
    }

    public Coord3 getPosition() {
        return position;
    }

    public void setPosition(Coord3 position) {
        this.position = position;
    }
}
