package de.fritz.raytrace.math;

public class Coord3 {

    double x;
    double y;
    double z;

    public Coord3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void add(double x, double y, double z){
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public Coord3 add(Vector3 other){
        return new Coord3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Coord3 multiply(double scalar){
        return new Coord3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

}
