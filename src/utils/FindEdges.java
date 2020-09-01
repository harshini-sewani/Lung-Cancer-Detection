/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import ij.ImagePlus;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import java.awt.Graphics;
import java.awt.Image;
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
public class FindEdges {
  public static String getEdgeImage(String filename) throws IOException
    {
        
          int find=filename.indexOf("threshold.jpg");
                   System.out.println("find "+find);
                String name=filename.substring(0,find) ;
                System.out.println("name "+name);
        File file =new File("images/"+name+".jpg/"+filename);
             
           
             BufferedImage image1 = ImageIO.read(file);
                         
                // Image image=null;
              ImageProcessor processor;
              processor=new ColorProcessor(image1);
             ImagePlus p;
             processor.findEdges();
             
             
             Image image=processor.createImage();
             
            BufferedImage img=new BufferedImage(image.getWidth(null),image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY)  ;
             //FileSaver fs=new FileSaver((ImagePus));
            Graphics g=img.getGraphics();
            g.drawImage(image,0,0,null);
            
          String path ="images/"+name+".jpg/"+name+"ijedge.jpg";
                
		ImageIO.write(img, "jpg", new File(path));
                return path;
           
    }
     public static void main(String[] args) {
        try {
            String filename="malariathreshold.jpg";
            String thresh=getEdgeImage(filename);
            
        } catch (IOException ex) {
            Logger.getLogger(Thresholding.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
}
