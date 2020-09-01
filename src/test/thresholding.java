/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
public class thresholding {
    public static BufferedImage Threshold(BufferedImage img,int requiredThresholdValue) {
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
		
		return finalThresholdImage;
	}
    public static void main(String[] args) {
        try {
            String filename="image002.jpg";
              //File file =new File("images/"+filename+"/"+"malariagrey.jpg");
                            //System.out.println("fie "+file.getAbsolutePath());
                            File file =new File("images/malaria.jpg/"+filename);
                            BufferedImage image = ImageIO.read(file);
                            
                             int THRESHOLD = 140;
                              BufferedImage edges = Threshold(image,THRESHOLD);
                  //  String path="images/"+filename+"/"+"malariabinary.jpg";
                              String path="images/malaria.jpg/image002binary.jpg";
                    ImageIO.write(edges, "jpg", new File(path));
                             
        } catch (IOException ex) {
            Logger.getLogger(thresholding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}