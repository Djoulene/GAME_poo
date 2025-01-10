package main;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
public class Utilitytool {
public BufferedImage scaleImage(BufferedImage original, int width , int height) {
    // Use a standard image type for compatibility
    BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
    Graphics2D g2 = scaledImage.createGraphics();


    g2.drawImage(original, 0, 0, width, height, null);
    g2.dispose();

    return scaledImage;
}
}
