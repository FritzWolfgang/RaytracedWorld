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
        if(this.pitch+pitch >= 70){
            this.pitch = 69.9;
        }
        if(this.pitch+pitch <= -70){
            this.pitch = -69.9;
        }
        this.pitch += pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

}
