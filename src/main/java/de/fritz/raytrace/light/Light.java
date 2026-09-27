package de.fritz.raytrace.light;

public abstract class Light {

    public static final int AMBIENT = 0;
    public static final int POINT = 1;
    public static final int DIRECTIONAL = 2;

    int type;
    double intensity;

    public Light(int type,  double intensity) {
        this.type = type;
        this.intensity = intensity;
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
}
