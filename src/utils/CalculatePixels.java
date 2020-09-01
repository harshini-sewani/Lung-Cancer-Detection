/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author admin
 */
public class CalculatePixels {
    public int getPixels(File process)
    {
        int redCount = 0, greenCount = 0, blueCount = 0,whiteCount = 0,blackCount = 0;
        try {
            BufferedImage image = ImageIO.read(process);
           
            int red   = Color.red.getRGB();
            int green = Color.green.getRGB();
            int blue  = Color.blue.getRGB();
            int white = Color.white.getRGB();
            int black = Color.black.getRGB();
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
                    if(pixel == blackCount) blackCount++; 
                }
            }
            System.out.printf("redCount = %d  greenCount = %d  blueCount = %d whiteCount = %d%n",
                               redCount, greenCount, blueCount,whiteCount);
            System.out.println("blackCount "+blackCount);
            
        } catch (IOException ex) {
            Logger.getLogger(CalculatePixels.class.getName()).log(Level.SEVERE, null, ex);
        }
      return whiteCount;
    }
    public static void main(String[] args) {
        int whitePixels=0;
        String filename="File\\detect.jpg";
           File file=new File("");
       // BufferedImage img1=new BufferedImage(  new File(""));
//           
//           ImageIO.write(img1, "jpg", new File(filename));
//                System.out.println("img1 "+filename);
////                String filename="C:\\Documents and Settings\\admin\\My Documents\\rahul\\Java file\\Traffic Congestion Control\\images\\11-cars.jpg";
//                ConvertToGrayScale gray=new ConvertToGrayScale();   
//                File grayfile=gray.getGrayImage(filename);
//                Thresholding thresh=new Thresholding();
//                File finalfile=thresh.getThreshImage(grayfile);
//                CaculatePixels cp=new CaculatePixels();
//                whitePixels=cp.getPixels(finalfile);
//                System.out.println("whitePixels "+whitePixels);
         
    }
}
