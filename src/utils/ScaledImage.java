/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author admin
 */
public class ScaledImage {
    public Image getScaledImage(Image srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = resizedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();
    return resizedImg;
}
}
