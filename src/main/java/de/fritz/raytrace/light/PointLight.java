package de.fritz.raytrace.light;

import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.objects.Scene;
import de.fritz.raytrace.objects.shapes.Sphere;

import java.awt.*;

public class PointLight extends Light{

    Coord3 position;

    Sphere sphere;

    Scene scene;

    public PointLight(Scene scene, Coord3 position, double intensity) {
        super(scene, Light.POINT, intensity);
        this.position = position;
        this.scene = scene;
        updateSphere();
        scene.addShape(sphere);
    }

    void updateSphere(){
        scene.removeShape(sphere);
        sphere=new Sphere(scene, position, intensity*0.5, ColorUtils.multiplyColors(new Color(255, 232, 173), (float) (intensity*1.5)), true);
        scene.addShape(sphere);
    }

    public Coord3 getPosition() {
        return position;
    }

    public void setPosition(Coord3 position) {
        this.position = position;
        updateSphere();
    }

    public Sphere getSphere() {
        return sphere;
    }

}
