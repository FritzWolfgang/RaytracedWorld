package de.fritz.raytrace.light;

import java.awt.*;

public class ColorUtils {

    public static Color multiplyColors(Color base, float lightFactor) {
        int r = clamp(Math.round(base.getRed() * lightFactor));
        int g = clamp(Math.round(base.getGreen() * lightFactor));
        int b = clamp(Math.round(base.getBlue() * lightFactor));

        return new Color(r, g, b, base.getAlpha());
    }

    private static int clamp(int x) {
        if(x < 0) {
            return 0;
        }
        if(x > 255) {
            return 255;
        }
        return x;
    }

}
