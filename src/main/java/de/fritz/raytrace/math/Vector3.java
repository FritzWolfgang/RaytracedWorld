package de.fritz.raytrace.math;

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

    public Vector3 multiply(double scalar){
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3 divide(double divisor){
        return new Vector3(this.x / divisor, this.y / divisor, this.z / divisor);
    }


    public Vector3 rotateY(double radians, double vDistance) {
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double tan = Math.tan(radians);
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
