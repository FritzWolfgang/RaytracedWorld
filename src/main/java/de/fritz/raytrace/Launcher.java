package de.fritz.raytrace;

import de.fritz.raytrace.engine.Canvas;
import de.fritz.raytrace.engine.GameHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Launcher {

    void main() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setSize(1920, 1080);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());  // Use BorderLayout for automatic resizing

            de.fritz.raytrace.engine.Canvas canvas = new Canvas(frame);

            frame.add(canvas, BorderLayout.CENTER);

            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

            // Create a new blank cursor
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    cursorImg, new Point(0, 0), "blank cursor");

            frame.getContentPane().setCursor(blankCursor);

            new GameHandler(canvas);

            frame.setVisible(true);
        });
    }


}
