package test;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

/**
 *
 * @author admin
 */
public class ConvertImagetoArray {
    BufferedImage startImage;
    int[] startPixels;
    BufferedImage endImage;
    int[] endPixels;
    int width, height;
    public ConvertImagetoArray(BufferedImage img){
        startImage = img;
        width = img.getWidth();
        height = img.getHeight();
        startPixels = new int[width*height];
        img.getRGB(0, 0, width, height, startPixels, 0, width);
 
        //System.out.println(startPixels[0]);
       // xels();
         
        endImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) endImage.getData();
        raster.setPixels(0,0,width,height,endPixels);
         
        File file = new File("/RESULT.png");
}
}
