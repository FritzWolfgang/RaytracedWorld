package de.fritz.raytrace.objects;

import de.fritz.raytrace.light.AmbientLight;
import de.fritz.raytrace.light.DirectionalLight;
import de.fritz.raytrace.light.Light;
import de.fritz.raytrace.light.PointLight;
import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;

import java.awt.*;
import java.util.List;

public class Scene {

    List<Shape> shapes;
    List<Light> lights;

    public Scene() {
        shapes = new java.util.ArrayList<>();

        //spheres
        shapes.add(new Sphere(new Coord3(0,-1,2),1, new Color(255,0,0)));
        shapes.add(new Sphere(new Coord3(2,0,7),1, new Color(255,0,255)));
        shapes.add(new Sphere(new Coord3(-2,0,4),1, new Color(0,255,0)));
        shapes.add(new Sphere(new Coord3(0,-5002,0),5000, new Color(255,255,0)));

        //rects
        shapes.add(new Rectangle(new Coord3(0, 1, 4), 3,2, new Color(255, 115,0)));


        lights = new java.util.ArrayList<>();
        lights.add(new AmbientLight(0.2));
        lights.add(new PointLight(new Coord3(2,1,0), 0.6));
        lights.add(new DirectionalLight(new Vector3(1,4,4), 0.2));

    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public List<Light> getLights() {
        return lights;
    }
}
