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

    public Rectangle(Scene scene, Coord3 position, double width, double height, double rotX, double rotY, double rotZ, Color color) {
        super(scene, position, color);
        this.width = width;
        this.height = height;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.hw = width/2;
        this.hh = height/2;
        scene.addShape(this);
    }

    Coord3 point1() {
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
        return new Vector3(0, 0, 1).normalize();
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

}
