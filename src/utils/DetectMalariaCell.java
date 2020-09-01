/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author admin
 */

    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class DetectMalariaCell {
    public static String Threshold(String filename) throws IOException {
	int find=filename.indexOf("grey.jpg");
                   System.out.println("find "+find);
                String name=filename.substring(0,find) ;
                System.out.println("name "+name);
        File file =new File("images/"+name+".jpg/"+filename);
               System.out.println("file "+file.getAbsolutePath());
		BufferedImage img = ImageIO.read(file);
                int requiredThresholdValue=123;
                int height = img.getHeight();
		int width = img.getWidth();
		BufferedImage finalThresholdImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB) ;
		      
		int red = 0;
		int green = 0;
		int blue = 0;
		
		for (int x = 0; x < width; x++) {
			try {

				for (int y = 0; y < height; y++) {
					int color = img.getRGB(x, y);

					red = ImageOperations.getRed(color);
					green = ImageOperations.getGreen(color);
					blue = ImageOperations.getBlue(color);

					if((red+green+green)/3 < (int) (requiredThresholdValue)) {
							finalThresholdImage.setRGB(x,y,ImageOperations.mixColor(0, 0,0));
						}
						else {
							finalThresholdImage.setRGB(x,y,ImageOperations.mixColor(255, 255,255));
						}
					
				}
			} catch (Exception e) {
				 e.getMessage();
			}
		}
//		   int find=filename.indexOf("grey.jpg");
//                   System.out.println("find "+find);
//                String name=filename.substring(0,find) ;
                System.out.println("name "+ name);
                        
		// save the newly created image in a new file.
                String path ="images/"+name+".jpg/"+name+"binary.jpg";
                
		ImageIO.write(finalThresholdImage, "jpg", new File(path));
                return path;
	}
    public static void main(String[] args) {
        try {
            String filename="image002grey.jpg";
            String edges = Threshold(filename);
                
            System.out.println("edges "+edges);
                             
        }
        catch (IOException ex) {
            Logger.getLogger(DetectMalariaCell.class.getName()).log(Level.SEVERE, null, ex);
        }    }


}
