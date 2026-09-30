package de.fritz.raytrace.engine;

import de.fritz.raytrace.interact.KeyboardHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

public class Canvas extends JPanel {




    KeyboardHandler keyHandler = new KeyboardHandler(this);

    public static Viewport viewport;

    private static Robot robo;
    private static boolean recenteringMouse = false;
    private static final double MOUSE_SENSITIVITY = 0.1;

    private final AtomicInteger pendingMouseX = new AtomicInteger();
    private final AtomicInteger pendingMouseY = new AtomicInteger();

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

    private void moved(MouseEvent arg0) {
        if (recenteringMouse) {
            recenteringMouse = false;
            return;
        }

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        Point screenPos = getLocationOnScreen();
        recenteringMouse = true;
        robo.mouseMove(screenPos.x + centerX, screenPos.y + centerY);

        int moveX = arg0.getX() - centerX;
        int moveY = arg0.getY() - centerY;
        pendingMouseX.addAndGet(moveX);
        pendingMouseY.addAndGet(moveY);
    }

    void update() {
        int mouseX = pendingMouseX.getAndSet(0);
        int mouseY = pendingMouseY.getAndSet(0);

        viewport.camera.adjustDirection(
                mouseX * MOUSE_SENSITIVITY,
                -mouseY * MOUSE_SENSITIVITY
        );
    }

    BufferedImage img;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        img = viewport.renderFrame();
        g2d.drawImage(img, (getWidth()/2) - img.getWidth()/2, (getHeight()/2) - img.getHeight()/2, null);
    }





}
