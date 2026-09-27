package de.fritz.raytrace.objects;

import de.fritz.raytrace.math.Coord3;

public class Camera extends Coord3 {

    double yaw;
    double pitch;

    public Camera(double x, double y, double z, double yaw, double pitch) {
        super(x, y, z);
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void adjustDirection(double yaw, double pitch){
        this.yaw += yaw;
        this.pitch += pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }
}
