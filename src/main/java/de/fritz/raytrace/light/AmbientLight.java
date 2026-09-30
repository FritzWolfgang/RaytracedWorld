package de.fritz.raytrace.light;

import de.fritz.raytrace.objects.Scene;

public class AmbientLight extends Light{

    public AmbientLight(Scene scene, double intensity) {
        super(scene, Light.AMBIENT, intensity);
    }

}
