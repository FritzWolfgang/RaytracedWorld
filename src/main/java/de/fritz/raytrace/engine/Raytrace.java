package de.fritz.raytrace.engine;

import de.fritz.raytrace.light.ColorUtils;
import de.fritz.raytrace.light.DirectionalLight;
import de.fritz.raytrace.light.Light;
import de.fritz.raytrace.light.PointLight;
import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.Rectangle;
import de.fritz.raytrace.objects.Scene;
import de.fritz.raytrace.objects.Shape;
import de.fritz.raytrace.objects.Sphere;

import java.awt.*;
import java.util.Vector;

public class Raytrace {

    public static Color traceRay(Vector3 d, Coord3 o, double t_min, double t_max, Color backgroundColor, Scene scene) {
        double closest_t = Double.MAX_VALUE;
        Shape closest = null;
        for (Shape shape : scene.getShapes()) {
            if (shape instanceof Sphere) {
                Sphere s = (Sphere) shape;

                double[] intersects = calculateSphereRayIntersects(d, o, s);
                if (intersects != null) {
                    if (intersects[0] > t_min && intersects[0] < t_max) {
                        if (intersects[0] < closest_t) {
                            closest_t = intersects[0];
                            closest = s;
                            //System.out.println("closest: " + closest);
                        }
                    }
                    if (intersects[1] > t_min && intersects[1] < t_max) {
                        if (intersects[1] < closest_t) {
                            closest_t = intersects[1];
                            closest = s;
                            //System.out.println("closest: " + closest);
                        }
                    }
                }
            } else if (shape instanceof Rectangle) {
                Rectangle r = (Rectangle) shape;
                double t = calculateRectRayIntersect(d, o, r);
                if (t > t_min && t < t_max) {
                    if(t < closest_t) {
                        closest_t = t;
                        closest = r;
                    }
                }
            }
        }
        if (closest == null) {
            return backgroundColor;
        }

        Coord3 p = o.add(d.multiply(closest_t));
        Vector3 n;
        if(closest instanceof Sphere) {
            n = Vector3.calculateVec3d(closest.getCenter(), p);
        }else {
            n = ((Rectangle) closest).getNormal();
        }


        //normalize
        n = n.divide(n.computeLength());

        double lighting = computeLighting(p, n, scene);
        return ColorUtils.multiplyColors(closest.getColor(), (float) lighting);
    }


    public static double[] calculateSphereRayIntersects(Vector3 d, Coord3 o, Sphere sphere) {
        double[] intersects = new double[2];


        Vector3 co = Vector3.calculateVec3d(sphere.getCenter(), o);

        double a = Vector3.dotProduct(d, d);
        double b = 2 * Vector3.dotProduct(co, d);
        double c = Vector3.dotProduct(co, co) - (sphere.getRadius() * sphere.getRadius());

        double discD = b * b - 4 * a * c;
        if (discD < 0) {
            return null;
        }
        intersects[0] = (-b + Math.sqrt(discD)) / (2 * a);
        intersects[1] = (-b - Math.sqrt(discD)) / (2 * a);

        return intersects;
    }

    public static double calculateRectRayIntersect(Vector3 d, Coord3 o, Rectangle rect) {
        double rectZ = rect.getMinPoint().getZ();

        double t = (rectZ - o.getZ()) / d.getZ();

        Vector3 hitPoint = Vector3.calculateVec3d(o, d.multiply(t));

        boolean hit = hitPoint.getX() >= rect.getMinPoint().getX() && hitPoint.getX() <= rect.getMaxPoint().getX() &&
                hitPoint.getY() >= rect.getMinPoint().getY() && hitPoint.getY() <= rect.getMaxPoint().getY();

        if (hit) {
            return t;
        }
        return -1; // No intersection
    }

    public static double computeLighting(Coord3 point, Vector3 normal, Scene scene) {
        double i = 0.0;
        for (Light light : scene.getLights()) {
            if (light.getType() == Light.AMBIENT) {
                i += light.getIntensity();
            } else {
                Vector3 l;
                if (light.getType() == Light.POINT) {
                    PointLight pointLight = (PointLight) light;
                    l = Vector3.calculateVec3d(point, pointLight.getPosition());
                } else {
                    DirectionalLight dLight = (DirectionalLight) light;
                    l = dLight.getDirection();/*.multiply(-1);*/
                }

                if (Vector3.dotProduct(l, normal) > 0) {
                    i += light.getIntensity() * (Vector3.dotProduct(l, normal) / (normal.computeLength() * l.computeLength()));
                }
            }
        }
        return i;
    }

}
