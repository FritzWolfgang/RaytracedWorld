package de.fritz.raytrace.light;

public class AmbientLight extends Light{



    public AmbientLight(double intensity) {
        super(Light.AMBIENT, intensity);
    }
}
