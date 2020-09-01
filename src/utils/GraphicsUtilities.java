package utils;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class GraphicsUtilities {

    public static BufferedImage resizeImage(BufferedImage image,int newWidth, int newHeight) {

        if (image.getWidth() == newWidth && image.getHeight() == newHeight) {
            return image;
        }
        BufferedImage temp = createCompatibleImage(image, newWidth, newHeight);
        Graphics2D g2 = temp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, newWidth, newHeight, null);
        g2.dispose();
        return temp;
    }

    public static BufferedImage createCompatibleImage(BufferedImage image,int width, int height) {
        return getGraphicsConfiguration().createCompatibleImage(width, height,
                image.getTransparency());
    }

    public static GraphicsConfiguration getGraphicsConfiguration() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }
}