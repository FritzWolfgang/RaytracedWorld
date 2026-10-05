package de.fritz.raytrace.light;

import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.Scene;

public abstract class Light{

    public static final int AMBIENT = 0;
    public static final int POINT = 1;
    public static final int DIRECTIONAL = 2;

    int type;
    double intensity;

    public Light(Scene scene, int type, double intensity) {
        this.type = type;
        this.intensity = intensity;
        scene.addLight(this);
    }

    public int getType() {
        return type;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public static double computeLighting(Coord3 point, Vector3 normal, Scene scene) {

        //determine Light value for a point
        double i = 0.0;
        for (Light light : scene.getLights()) {

            if (light.getType() == Light.AMBIENT) { //ambient light is always there
                i += light.getIntensity();
            } else {
                Vector3 l; //determine vector from point to light
                if (light.getType() == Light.POINT) {
                    PointLight pointLight = (PointLight) light;
                    l = Vector3.calculateVec3d(point, pointLight.getPosition());
                } else {
                    DirectionalLight dLight = (DirectionalLight) light;
                    l = dLight.getDirection();/*.scale(-1);*/
                }

                if (Vector3.dotProduct(l, normal) > 0) { //make sure only fronts are added
                    //calculate intensity based on angle
                    i += light.getIntensity() * (Vector3.dotProduct(l, normal) / (normal.computeLength() * l.computeLength()));
                }
            }
        }
        return i;
    }

}
