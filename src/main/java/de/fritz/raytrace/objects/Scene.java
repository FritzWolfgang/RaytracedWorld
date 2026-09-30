package de.fritz.raytrace.objects;

import de.fritz.raytrace.light.AmbientLight;
import de.fritz.raytrace.light.DirectionalLight;
import de.fritz.raytrace.light.Light;
import de.fritz.raytrace.light.PointLight;
import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.shapes.Rectangle;
import de.fritz.raytrace.objects.shapes.Shape;
import de.fritz.raytrace.objects.shapes.Sphere;

import java.awt.*;
import java.util.List;

public class Scene {

    List<de.fritz.raytrace.objects.shapes.Shape> shapes;
    List<Light> lights;

    public Scene() {
        shapes = new java.util.ArrayList<>();
        lights = new java.util.ArrayList<>();

        //spheres
        new Sphere(this, new Coord3(0, -1, 2), 1, new Color(255, 0, 0));
        new Sphere(this, new Coord3(2, 0, 7), 1, new Color(255, 0, 255));
        new Sphere(this, new Coord3(-2, 0, 4), 1, new Color(0, 255, 0));


        //rects
        new Rectangle(this, new Coord3(0, 1
                , 4), 3,2, Math.PI/2, Math.PI*1.5,Math.PI*0.2, new Color(16, 64, 220));


        new AmbientLight(this,0.2);
        new PointLight(this, new Coord3(2, 1, 0), 0.6);
        new DirectionalLight(this, new Vector3(1, 4, 4), 0.2);



    }

    public List<de.fritz.raytrace.objects.shapes.Shape> getShapes() {
        return shapes;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void addShape(de.fritz.raytrace.objects.shapes.Shape shape) {
        shapes.add(shape);
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public void removeLight(Light light) {
        lights.remove(light);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }
}