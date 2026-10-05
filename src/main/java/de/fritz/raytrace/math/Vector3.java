package de.fritz.raytrace.math;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Vector3 {

    double x;
    double y;
    double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double computeLength(){
        return Math.sqrt(Vector3.dotProduct(this,this));
    }

    public Vector3 scale(double scalar){
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3 divide(double divisor){
        return new Vector3(this.x / divisor, this.y / divisor, this.z / divisor);
    }


    public Vector3 normalize(){
        return this.divide(this.computeLength());
    }

    public static Vector3 getRotateX(Vector3 m, double radians){
        double x = m.getX();
        double y = m.getY()*cos(radians)+m.getZ()*sin(radians);
        double z = m.getZ()*cos(radians)-m.getY()*sin(radians);
        return new Vector3(x,y,z);
    }

    public void rotateX(double radians){
        this.y = this.y*cos(radians)+this.z*sin(radians);
        this.z = this.z*cos(radians)-this.y*sin(radians);
    }

    public static Vector3 getRotateY(Vector3 m, double radians){
        double x = m.getX()*cos(radians)+m.getZ()*sin(radians);
        double y = m.getY();
        double z = m.getZ()*cos(radians)-m.getX()*sin(radians);
        return new Vector3(x,y,z);
    }

    public void rotateY(double radians){
        this.x = this.x*cos(radians)+this.z*sin(radians);
        this.z = this.z*cos(radians)-this.x*sin(radians);
    }

    public static Vector3 getRotateZ(Vector3 m, double radians){
        double x = m.getX()*cos(radians)+m.getY()*sin(radians);
        double y = m.getY()*cos(radians)-m.getX()*sin(radians);
        double z = m.getZ();
        return new Vector3(x,y,z);
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

    public Vector3 rotateY(double radians, double vDistance) {
        double cos = cos(radians);
        double sin = Math.sin(radians);
        return new Vector3(
                this.x * cos + this.z * sin,
                this.y,
                -this.x * sin + this.z * cos
        );
    }

    public Vector3 rotateX(double radians, double vDistance) {
        double tan = Math.tan(radians);
        return new Vector3(
                this.x,
                this.y+tan*vDistance,
                this.z
        );
    }

    public static double dotProduct(Vector3 v1, Vector3 v2){
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vector3 calculateVec3d(Coord3 coord1, Coord3 coord2){
        return new Vector3(coord2.getX() - coord1.getX(), coord2.getY() - coord1.getY(), coord2.getZ() - coord1.getZ());
    }

    public static Vector3 calculateVec3d(Coord3 coord1, Vector3 vector){
        return new Vector3(coord1.getX() + vector.getX(), coord1.getY() + vector.getY(), coord1.getZ() + vector.getZ());
    }







    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
