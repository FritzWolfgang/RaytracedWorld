package de.fritz.raytrace.engine;

import de.fritz.raytrace.Statics;
import de.fritz.raytrace.math.Coord3;
import de.fritz.raytrace.math.Vector3;
import de.fritz.raytrace.objects.Camera;
import de.fritz.raytrace.objects.Scene;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Viewport {

   /*
   Camera O

   Canvas C with
    - dimensions Cw and Vh

   Viewport V with
    - distance d to O (Vz)
    - dimensions Vw and Vh

    Vector D |V-O| between Viewport(-pixels) and Camera

   if D hits Sphere (quadratic): draw sphere color
   else: draw backgroundColor



   */


    public Camera camera;



    double Vwidth = 1.0;
    double Vheight = 1.0;

    double distance = 1.2;

    Scene scene = new Scene();

    public static final Color backgroundColor = new Color(50, 50,50);

    Canvas canvas;


    public Viewport(Canvas canvas) {
        this.canvas = canvas;

        camera = new Camera(0.0,0.0,0.0,0.0,0);
    }


    BufferedImage img = new BufferedImage(Statics.CANVAS_WIDTH, Statics.CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);

    public BufferedImage renderFrame(){
        double yaw = Math.toRadians(camera.getYaw());
        double pitch =  Math.toRadians(camera.getPitch());

        double pixelScaleX = Vwidth / Statics.CANVAS_WIDTH;
        double pixelScaleY = Vheight / Statics.CANVAS_HEIGHT;
        double halfWidth = Statics.CANVAS_WIDTH / 2.0;
        double halfHeight = Statics.CANVAS_HEIGHT / 2.0;

        for (int y = 0; y < Statics.CANVAS_HEIGHT; y++) {
            double viewportY = (halfHeight - y) * pixelScaleY;

            for (int x = 0; x < Statics.CANVAS_WIDTH; x++) {
                double viewportX = (x - halfWidth) * pixelScaleX;

                Coord3 viewportCoord3 = new Coord3(
                        camera.getX() + viewportX,
                        camera.getY() + viewportY,
                        camera.getZ() + distance
                );
                Vector3 d = Vector3.calculateVec3d(camera, viewportCoord3).
                        rotateY((yaw), distance).
                        rotateX(pitch, distance);

                Color color = Raytrace.traceRay(d, camera, 1.0, Double.MAX_VALUE, backgroundColor, scene);

                img.setRGB(x, y, color.getRGB());
            }
        }

        return img;
    }





}
