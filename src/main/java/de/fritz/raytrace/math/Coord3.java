package de.fritz.raytrace.math;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

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

    public static Coord3 getRotateX(Coord3 m, double radians){
        double x = m.getX();
        double y = m.getY()*cos(radians)+m.getZ()*sin(radians);
        double z = m.getZ()*cos(radians)-m.getY()*sin(radians);
        return new Coord3(x,y,z);
    }

    public void rotateX(double radians){
        this.y = this.y*cos(radians)+this.z*sin(radians);
        this.z = this.z*cos(radians)-this.y*sin(radians);
    }

    public static Coord3 getRotateY(Coord3 m, double radians){
        double x = m.getX()*cos(radians)+m.getZ()*sin(radians);
        double y = m.getY();
        double z = m.getZ()*cos(radians)-m.getX()*sin(radians);
        return new Coord3(x,y,z);
    }

    public void rotateY(double radians){
        this.x = this.x*cos(radians)+this.z*sin(radians);
        this.z = this.z*cos(radians)-this.x*sin(radians);
    }

    public static Coord3 getRotateZ(Coord3 m, double radians){
        double x = m.getX()*cos(radians)+m.getY()*sin(radians);
        double y = m.getY()*cos(radians)-m.getX()*sin(radians);
        double z = m.getZ();
        return new Coord3(x,y,z);
    }

    public void rotateZ(double radians){
        this.x = this.x*cos(radians)+this.y*sin(radians);
        this.y = this.y*cos(radians)-this.x*sin(radians);
    }

    public void rotate(double xRad, double yRad, double zRad){
        rotateX(xRad);
        rotateY(yRad);
        rotateZ(zRad);
    }

    public static Coord3 getRotate(Coord3 m, double xRad, double yRad, double zRad){
        Coord3 rotating = m;
        rotating = getRotateX(rotating,xRad);
        rotating = getRotateY(rotating,yRad);
        rotating = getRotateZ(rotating,zRad);
        return rotating;
    }


    public Coord3 add(Vector3 other){
        return new Coord3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Coord3 scale(double scalar){
        return new Coord3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

}
