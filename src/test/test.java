/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

  

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
 
public class test {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Documents and Settings\\admin\\My Documents\\rahul\\Java file\\Traffic Congestion Control\\images\\throsold-12threshold.jpg";
        BufferedImage image = ImageIO.read(new File(path));
        int redCount = 0, greenCount = 0, blueCount = 0,whiteCount=0;
        int red   = Color.red.getRGB();
        int green = Color.green.getRGB();
        int blue  = Color.blue.getRGB();
        int white = Color.white.getRGB();
        System.out.printf("red = %d  green = %d  blue = %d white = %d%n ",
                           red, green, blue, white);
        int w = image.getWidth();
        int h = image.getHeight();
        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);
                if(pixel == red)   redCount++;
                if(pixel == green) greenCount++;
                if(pixel == blue)  blueCount++;
                if(pixel == white)  whiteCount++;
            }
        }
        System.out.printf("redCount = %d  greenCount = %d  blueCount = %d whiteCount = %d%n",
                           redCount, greenCount, blueCount,whiteCount);
    }
}
    
    

