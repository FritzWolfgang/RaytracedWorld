package de.fritz.raytrace.interact;


import de.fritz.raytrace.engine.Canvas;
import de.fritz.raytrace.engine.Time;
import de.fritz.raytrace.engine.Viewport;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

    private boolean w_pressed = false;
    private boolean s_pressed = false;
    private boolean a_pressed = false;
    private boolean d_pressed = false;
    private boolean space_pressed = false;
    private boolean shift_pressed = false;

    Canvas canvas;

    public KeyboardHandler(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            w_pressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            s_pressed = true;
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            a_pressed = true;
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            d_pressed = true;
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            space_pressed = true;
        } else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift_pressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            w_pressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            s_pressed = false;
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            a_pressed = false;
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            d_pressed = false;
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            space_pressed = false;
        } else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift_pressed = false;
        }
    }

    public boolean isWPressed() { return w_pressed; }
    public boolean isSPressed() { return s_pressed; }
    public boolean isAPressed() { return a_pressed; }
    public boolean isDPressed() { return d_pressed; }
    public boolean isSpacePressed() { return space_pressed; }
    public boolean isShiftPressed() { return shift_pressed; }

    public void handleKeys(){
        Viewport viewport = Canvas.viewport;
        double movement = 6.0 * Time.getDeltaTime();

        double yaw = Math.toRadians(viewport.camera.getYaw());
        double forwardX = Math.sin(yaw);
        double forwardZ = Math.cos(yaw);
        double rightX = Math.cos(yaw);
        double rightZ = -Math.sin(yaw);

        if(isWPressed()){
            viewport.camera.add(forwardX * movement, 0, forwardZ * movement);
        }
        if(isSPressed()){
            viewport.camera.add(-forwardX * movement, 0, -forwardZ * movement);
        }
        if(isAPressed()){
            viewport.camera.add(-rightX * movement, 0, -rightZ * movement);
        }
        if(isDPressed()){
            viewport.camera.add(rightX * movement, 0, rightZ * movement);
        }
        if(isSpacePressed()){
            viewport.camera.add(0, movement, 0);
        }
        if(isShiftPressed()){
            viewport.camera.add(0, -movement, 0);
        }
    }

}
