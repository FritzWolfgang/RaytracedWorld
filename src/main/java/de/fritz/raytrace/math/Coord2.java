package de.fritz.raytrace.math;

public class Coord2 {

    double x;
    double y;
    public Coord2(double x, double y) {
        this.x = x;
        this.y = y;
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

    public Coord3 convert3d(double z){
        return new Coord3(x, y, z);
    }

    public void add(double x, double y){
        this.x += x;
        this.y += y;
    }

}
