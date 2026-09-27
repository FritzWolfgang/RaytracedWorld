package de.fritz.raytrace.engine;

import de.fritz.raytrace.interact.KeyboardHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel {

    public static final int CANVAS_WIDTH = 1000,
                            CANVAS_HEIGHT = 1000;


    KeyboardHandler keyHandler = new KeyboardHandler();

    static Viewport viewport;

    private static Robot robo;
    private static boolean recenteringMouse = false;

    static JFrame frame;
    public Canvas(JFrame frame) {

        Canvas.frame = frame;

        viewport = new Viewport(this);

        this.addKeyListener(keyHandler);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                moved(arg0);
            }

            @Override
            public void mouseMoved(MouseEvent arg0) {
                moved(arg0);
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();

        try {
            robo = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public static void moved(MouseEvent arg0) {
        if (recenteringMouse) {
            //prediction
            int centerX = viewport.canvas.getWidth() / 2;
            int centerY = viewport.canvas.getHeight() / 2;
            int moveX = arg0.getX() - centerX;
            int moveY = arg0.getY() - centerY;
            viewport.camera.adjustDirection(moveX * 0.1, -moveY * 0.1);
            recenteringMouse = false;
            return;
        }

        int centerX = viewport.canvas.getWidth() / 2;
        int centerY = viewport.canvas.getHeight() / 2;
        Point screenPos = viewport.canvas.getLocationOnScreen();
        recenteringMouse = true;
        robo.mouseMove(screenPos.x + centerX, screenPos.y + centerY);

        int moveX = arg0.getX() - centerX;
        int moveY = arg0.getY() - centerY;
        viewport.camera.adjustDirection(moveX * 0.1, -moveY * 0.1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        update();
        viewport.renderFrame(g2d);
    }

    void update(){
        double yaw = Math.toRadians(viewport.camera.getYaw());
        double forwardX = Math.sin(yaw);
        double forwardZ = Math.cos(yaw);
        double rightX = Math.cos(yaw);
        double rightZ = -Math.sin(yaw);

        if(keyHandler.isWPressed()){
            viewport.camera.add(forwardX * 0.1, 0, forwardZ * 0.1);
        }
        if(keyHandler.isSPressed()){
            viewport.camera.add(-forwardX * 0.1, 0, -forwardZ * 0.1);
        }
        if(keyHandler.isAPressed()){
            viewport.camera.add(-rightX * 0.1, 0, -rightZ * 0.1);
        }
        if(keyHandler.isDPressed()){
            viewport.camera.add(rightX * 0.1, 0, rightZ * 0.1);
        }
    }


}
