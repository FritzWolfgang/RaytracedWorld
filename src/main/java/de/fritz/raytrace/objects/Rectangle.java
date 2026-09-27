package de.fritz.raytrace.objects;

import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;

import java.awt.*;

public class Rectangle extends Shape {

    double width;
    double height;

    public Rectangle(Coord3 position, double width, double height,Color color) {
        super(position, color);
        this.width = width;
        this.height = height;
    }


    public Coord3 getMinPoint(){
        return new Coord3(center.getX()-width/2,center.getY()-height/2,center.getZ());
    }

    public Coord3 getMaxPoint(){
        return new Coord3(center.getX()+width/2,center.getY()+height/2,center.getZ());
    }

    public Vector3 getNormal() {
        return new Vector3(0, 0, 1);
    }
}
