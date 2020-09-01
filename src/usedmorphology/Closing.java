/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usedmorphology;

/**
 *
 * @author admin
 */

    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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

  

public class Closing
{
    public static String getClosingImage(String filename) throws IOException
    {
        
          int find=filename.indexOf(".jpg");
                   
          String name=filename.substring(0,find) ;
                
          File file =new File("images/"+filename+"/"+name+"dilation.jpg");
           
          BufferedImage image1 = ImageIO.read(file);
                         
                // Image image=null;
          ImageProcessor processor;
          processor=new ColorProcessor(image1);
         // ImagePlus p;
          processor.dilate();
             
          Image image=processor.createImage();
             
          BufferedImage img=new BufferedImage(image.getWidth(null),image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY)  ;
             //FileSaver fs=new FileSaver((ImagePus));
            Graphics g=img.getGraphics();
            g.drawImage(image,0,0,null);
            String path="images/"+filename+"/"+name+"closing.jpg";
            File out=new File(path);
            ImageIO.write(img, "jpg", out);
       
        return path;
           
    }
    
    public static void main(String[] args) {
       // getClosingImage();
     
    }

}
    
    



