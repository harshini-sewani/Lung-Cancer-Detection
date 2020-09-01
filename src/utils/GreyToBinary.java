/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author admin
 */
public class GreyToBinary {
    public static String  getGrayToBinary(String filename) throws IOException
{
    // Save the image file as a BufferedImage object
//    File file =new File("images/"+filename+"/"+"malariagrey.jpg");
//    System.out.println("fie "+file.getAbsolutePath());
   File file=new File("C:\\Documents and Settings\\admin\\Desktop\\Mujeeb\\MalariaDetectionIP\\images\\malaria.jpg\\Malaria2grey.jpg" );
		BufferedImage image = ImageIO.read(file);
		

                
                int width=image.getWidth();
                int height=image.getHeight();
                BufferedImage buff_red;
                buff_red = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                
Graphics r = buff_red.getGraphics();
r.drawImage(image, 0, 0, null);
               int[] process_red = new int[width * height];
int counter = 0;
 for(int i = 0; i < 256; i++) {
     for(int j = 0; j < 256; j++) {
         int clr = buff_red.getRGB(j, i);
         int red = (clr & 0x00ff0000) >> 16;
         red = (0xFF<<24)|(red<<16)|(red<<8)|red;
         process_red[counter] = red;
         counter++;
     }
}

//set threshold value for red element
int threshold = 100;
for (int x = 0; x < width; x++) {
for (int y = 0; y < height; y++) {
     int bin = (buff_red.getRGB(x, y) & 0x000000ff);
     if (bin < threshold)
               bin = 0;
     else
               bin = 255;
     buff_red.setRGB(x,y, 0xff000000 | bin << 16 | bin << 8 | bin);
     }
}
                
                
                int find=filename.indexOf(".");
                String name=filename.substring(0,find) ;
                
                        
		// save the newly created image in a new file.
                String path ="images/"+filename+"/"+name+"binary.jpg";
                System.out.println("path "+path);
		//ImageIO.write(buff_red, "jpg", new File(path));
               ImageIO.write(buff_red, "jpg", new File("C:\\Documents and Settings\\admin\\Desktop\\Mujeeb\\MalariaDetectionIP\\images\\malaria.jpg\\Malaria2binary.jpg" ));

                return path;

	}
    
    
    

    static String filename="malaria.jpg";
    public static void main(String[] args) {
        try {
            //  File file =new File("images/"+filename+"/"+filename);
             // System.out.println("fie "+file.getAbsolutePath());
       String path=getGrayToBinary(filename);
            System.out.println("main "+ path);
        } catch (IOException ex) {
            Logger.getLogger(GreyToBinary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 
    
}
