package de.fritz.raytrace.engine;

import de.fritz.raytrace.Statics;
import de.fritz.raytrace.light.ColorUtils;
import de.fritz.raytrace.light.Light;
import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.shapes.Rectangle;
import de.fritz.raytrace.objects.Scene;
import de.fritz.raytrace.objects.shapes.Shape;
import de.fritz.raytrace.objects.shapes.Sphere;

import java.awt.*;

public class Raytrace {

    public static Color traceRay(Vector3 d, Coord3 o, double t_min, double t_max, Color backgroundColor, Scene scene) {
        double closest_t = Double.MAX_VALUE;
        Shape closest = null;

        //trace the ray and figure out the shape in front
        for (Shape shape : scene.getShapes()) {
            if (shape instanceof Sphere s) {

                //calculate the intersects with a sphere
                double[] intersects = calculateSphereRayIntersects(d, o, s);
                if (intersects != null) {
                    if (intersects[0] > t_min && intersects[0] < t_max) { //intersect in front
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
            } else if (shape instanceof Rectangle r) {

                //calculate the intersects with a rect
                double t = calculateRectRayIntersect(d, o, r);
                if (t > t_min && t < t_max) { //intersect in front
                    if(t < closest_t) {
                        closest_t = t;
                        closest = r;
                    }
                }
            }
        }

        //chess floor
        if(d.getY() <= 0){
            double tFloor = (Statics.floorY-o.getY())/d.getY();
            if (tFloor > t_min && tFloor < t_max) {
                if(tFloor < closest_t) {
                    closest_t = tFloor;
                    Coord3 hitPoint = o.add(d.scale(closest_t));
                    int fX = (int) Math.floor(hitPoint.getX());
                    int fZ = (int) Math.floor(hitPoint.getZ());

                    if((fX + fZ) % 2 != 0){
                        //check
                        return new Color(28, 28, 28);
                    }else{
                        //uncheck
                        return new Color(180, 180, 180);
                    }
                }

            }
        }

        if (closest == null && closest_t==t_max) {
            return backgroundColor;
        }
        assert closest != null;

        if(closest instanceof Sphere){
            if(((Sphere) closest).isPointLight()){
                return closest.getColor();
            }
        }

        //Lambertian lighting

        //determine normal vector on Shape for lighting
        Coord3 p = o.add(d.scale(closest_t));

        Vector3 n = closest.getLightingNormal(p, d);

        double lighting = Light.computeLighting(p, n, scene);
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
        Coord3[] points = rect.getPoints();
        Coord3 p0 = points[0];
        Vector3 edgeU = rect.getEdgeU();
        Vector3 edgeV = rect.getEdgeV();
        Vector3 normal = rect.getNormal();

        double denominator = Vector3.dotProduct(normal, d);

        // Ray parallel to plane
        if (Math.abs(denominator) < 1e-9) {
            return -1;
        }

        Vector3 originToRay = Vector3.calculateVec3d(p0, o);

        double t = -Vector3.dotProduct(normal, originToRay)
                / denominator;

        if (t <= 0) {
            return -1;
        }

        Coord3 hitPoint = o.add(d.scale(t));
        Vector3 p0ToHit = Vector3.calculateVec3d(p0, hitPoint);

        double u = Vector3.dotProduct(p0ToHit, edgeU)
                / rect.getEdgeULengthSquared();

        double v = Vector3.dotProduct(p0ToHit, edgeV)
                / rect.getEdgeVLengthSquared();

        boolean inside = u >= 0 && u <= 1 && v >= 0 && v <= 1;

        return inside ? t : -1;
    }


}
