package de.fritz.raytrace.engine;

import de.fritz.raytrace.math.Coord2;
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


    Camera camera;



    double Vwidth = 1.0;
    double Vheight = 1.0;

    double distance = 1.0;

    Scene scene = new Scene();

    public static final Color backgroundColor = new Color(50, 50,50);

    Canvas canvas;


    public Viewport(Canvas canvas) {
        this.canvas = canvas;

        camera = new Camera(0.0,0.0,0.0,0.0,0);
    }



    public void renderFrame(Graphics2D g2d){
        BufferedImage img = new BufferedImage(Canvas.CANVAS_WIDTH, Canvas.CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < Canvas.CANVAS_HEIGHT; y++) {
            for (int x = 0; x < Canvas.CANVAS_WIDTH; x++) {
                Coord2 viewportCoord2 = convertCanvasToViewport(new Coord2(x, y));
                Coord3 viewportCoord3 = new Coord3(
                        camera.getX() + viewportCoord2.getX(),
                        camera.getY() + viewportCoord2.getY(),
                        camera.getZ() + distance
                );
                Vector3 d = Vector3.calculateVec3d(camera, viewportCoord3).
                        rotateY(Math.toRadians(camera.getYaw()), distance).
                        rotateX(Math.toRadians(camera.getPitch()), distance);

                Color color = Raytrace.traceRay(d, camera, 1.0, Double.MAX_VALUE, backgroundColor, scene);

                img.setRGB(x, y, color.getRGB());
            }
        }



        g2d.drawImage(img, (canvas.getWidth()/2) - img.getWidth()/2, (canvas.getHeight()/2) - img.getHeight()/2, null);
    }

    Coord2 convertCanvasToViewport(Coord2 pixelCoord) {
        double x = pixelCoord.getX();
        double y  = pixelCoord.getY();
        double vx = (x - Canvas.CANVAS_WIDTH / 2.0) * (Vwidth / Canvas.CANVAS_WIDTH);
        double vy = (Canvas.CANVAS_HEIGHT / 2.0 - y) * (Vheight / Canvas.CANVAS_HEIGHT);
        return new Coord2(vx, vy);
    }



}
