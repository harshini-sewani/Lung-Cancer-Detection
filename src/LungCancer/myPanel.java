/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LungCancer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public final class myPanel extends JPanel {

    Image bi;
    ImageIcon ii;
    String FileName = "";
    String setImage = "";

    public myPanel() {
        try {
            String path = new File(".").getCanonicalPath();
            bi = Toolkit.getDefaultToolkit().getImage(path + "/3_Loading-30302.gif");
            ii = new ImageIcon(bi);
            setSize(ii.getIconWidth(), ii.getIconHeight());
        } catch (IOException ex) {
        }

    }

    //======================================================================================
    public myPanel(String filename) {
        try {
            bi = Toolkit.getDefaultToolkit().getImage(filename);
            ii = new ImageIcon(bi);
            setSize(ii.getIconWidth(), ii.getIconHeight());
        } catch (Exception ex) {
        }
    }

    public myPanel(File file, int width, int height) {
        try {
            BufferedImage bi = ImageIO.read(file);
            setSize(width, height);
            this.bi = linearResizeBi(bi, width, height);
        } catch (Exception ex) {
        }
    }

    //==========================================================================================
    public myPanel(BufferedImage bi, int width, int height) {
        try {
            setSize(width, height);
            this.bi = linearResizeBi(bi, width, height);
        } catch (Exception ex) {
        }
    }

    public BufferedImage linearResizeBi(BufferedImage origin, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        float xScale = (float) width / origin.getWidth();
        float yScale = (float) height / origin.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
        g.drawRenderedImage(origin, at);
        g.dispose();
        return resizedImage;
    }

    //===========================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bi, 0, 0, this);
    }
}