package de.fritz.raytrace.objects.shapes;

import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.Scene;

import java.awt.*;

public class Rectangle extends Shape {

    double width;
    double height;
    double hw;
    double hh;
    double rotX;
    double rotY;
    double rotZ;
    private final Coord3[] rotatedCorners;
    private Coord3[] points;
    private Vector3 edgeU;
    private Vector3 edgeV;
    private Vector3 normal;
    private Vector3 lightingNormal;
    private double edgeULengthSquared;
    private double edgeVLengthSquared;


    public Rectangle(Scene scene, Coord3 position, double width, double height, double rotX, double rotY, double rotZ, Color color) {
        super(scene, position, color);
        this.width = width;
        this.height = height;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.hw = width/2;
        this.hh = height/2;
        this.rotatedCorners = new Coord3[]{
                rotateCorner(-hw, -hh),
                rotateCorner(-hw, hh),
                rotateCorner(hw, -hh),
                rotateCorner(hw, hh)
        };

        calculatePoints();

    }

    private Coord3 rotateCorner(double x, double y) {
        return Coord3.getRotate(new Coord3(x, y, 0), rotX, rotY, rotZ);
    }

    /*Coord3 point1() {
        return getPosition().add(Vector3.calculateVec3d(
                new Coord3(0, 0, 0),
                Coord3.getRotate(new Coord3(-hw, -hh, 0), rotX, rotY, rotZ)));
    }

    Coord3 point2() {
        return getPosition().add(Vector3.calculateVec3d(
                new Coord3(0, 0, 0),
                Coord3.getRotate(new Coord3(-hw, hh, 0), rotX, rotY, rotZ)));
    }

    Coord3 point3() {
        return getPosition().add(Vector3.calculateVec3d(
                new Coord3(0, 0, 0),
                Coord3.getRotate(new Coord3(hw, -hh, 0), rotX, rotY, rotZ)));
    }

    Coord3 point4() {
        return getPosition().add(Vector3.calculateVec3d(
                new Coord3(0, 0, 0),
                Coord3.getRotate(new Coord3(hw, hh, 0), rotX, rotY, rotZ)));
    }

    public Coord3[] getPoints(){
        Coord3[] points = new Coord3[4];
        points[0] = point1();
        points[1] = point2();
        points[2] = point3();
        points[3] = point4();
        return points;
    }*/


    //rotate Corners instead of Points
    public void calculatePoints(){
        Coord3[] points = new Coord3[4];
        Coord3 position = getPosition();
        for (int i = 0; i < rotatedCorners.length; i++) {
            Coord3 corner = rotatedCorners[i];
            points[i] = new Coord3(
                    position.getX() + corner.getX(),
                    position.getY() + corner.getY(),
                    position.getZ() + corner.getZ());
        }
        this.points = points;
        Coord3 p0 = points[0];
        this.edgeU = Vector3.calculateVec3d(p0, points[2]);
        this.edgeV = Vector3.calculateVec3d(p0, points[1]);
        this.normal = cross(edgeU, edgeV);
        this.lightingNormal = new Vector3(normal.getX(), normal.getY(), normal.getZ()).normalize();
        this.edgeULengthSquared = Vector3.dotProduct(edgeU, edgeU);
        this.edgeVLengthSquared = Vector3.dotProduct(edgeV, edgeV);
    }

    public Coord3[] getPoints() {
        return points;
    }

    public double getMinX(){
        double lowest = Double.MAX_VALUE;
        for(Coord3 coord : getPoints()){
            if(coord.getX()<lowest){
                return coord.getX();
            }
        }
        return lowest;
    }

    public double getMaxX(){
        double highest = Double.MIN_VALUE;
        for(Coord3 coord : getPoints()){
            if(coord.getX()>highest){
                highest = coord.getX();
            }
        }
        return highest;
    }

    public double getMinY(){
        double lowest = Double.MAX_VALUE;
        for(Coord3 coord : getPoints()){
            if(coord.getY()<lowest){
                lowest = coord.getY();
            }
        }
        return lowest;
    }

    public double getMaxY(){
        double highest = -Double.MAX_VALUE;
        for(Coord3 coord : getPoints()){
            if(coord.getY()>highest){
                highest = coord.getY();
            }
        }
        return highest;
    }

    public double getMinZ(){
        double lowest = Double.MAX_VALUE;
        for(Coord3 coord : getPoints()){
            if(coord.getZ()<lowest){
                lowest = coord.getZ();
            }
        }
        return lowest;
    }

    public double getMaxZ(){
        double highest = -Double.MAX_VALUE;
        for(Coord3 coord : getPoints()){
            if(coord.getZ()>highest){
                highest = coord.getZ();
            }
        }
        return highest;
    }


    @Override
    public Vector3 getLightingNormal(Coord3 p) {
        return lightingNormal;
    }

    @Override
    public Vector3 getLightingNormal(Coord3 p, Vector3 rayDirection) {
        Vector3 normal = getLightingNormal(p);

        // Use normal of the face hit by ray.
        if (Vector3.dotProduct(normal, rayDirection) > 0) {
            return normal.scale(-1);
        }

        return normal;
    }

    public Coord3 getPosition() {
        return center;
    }

    public double getHw() {
        return hw;
    }

    public double getHh() {
        return hh;
    }

    public Vector3 getEdgeU() {
        return edgeU;
    }

    public Vector3 getEdgeV() {
        return edgeV;
    }

    public Vector3 getNormal() {
        return normal;
    }

    public double getEdgeULengthSquared() {
        return edgeULengthSquared;
    }

    public double getEdgeVLengthSquared() {
        return edgeVLengthSquared;
    }

    private static Vector3 cross(Vector3 a, Vector3 b) {
        return new Vector3(
                a.getY() * b.getZ() - a.getZ() * b.getY(),
                a.getZ() * b.getX() - a.getX() * b.getZ(),
                a.getX() * b.getY() - a.getY() * b.getX()
        );
    }

}
