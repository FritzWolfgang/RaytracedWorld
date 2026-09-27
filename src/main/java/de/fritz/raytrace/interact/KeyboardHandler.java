package de.fritz.raytrace.interact;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {

    private boolean w_pressed = false;
    private boolean s_pressed = false;
    private boolean a_pressed = false;
    private boolean d_pressed = false;

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
        }
    }

    public boolean isWPressed() { return w_pressed; }
    public boolean isSPressed() { return s_pressed; }
    public boolean isAPressed() { return a_pressed; }
    public boolean isDPressed() { return d_pressed; }
}
