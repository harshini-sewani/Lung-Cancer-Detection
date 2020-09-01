/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author admin
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
    import javax.imageio.ImageIO;
public class ConvertToGrayScale {

public static String  getGrayImage(String filename) throws IOException
{
    // Save the image file as a BufferedImage object
    File file =new File("images/"+filename+"/"+filename);
    System.out.println("fie "+file.getAbsolutePath());
		BufferedImage image = ImageIO.read(file);
		
		// Loop through all the pixels in the image (w = width, h = height)
		for(int w = 0; w < image.getWidth() ; w++)
		{
			for(int h = 0 ; h < image.getHeight() ; h++)
			{
				// BufferedImage.getRGB() saves the colour of the pixel as a single integer.
				// use Color(int) to grab the RGB values individually.
				Color color = new Color(image.getRGB(w, h));
				
				// use the RGB values to get their average.
				int averageColor = ((color.getRed() + color.getGreen() + color.getBlue()) / 3);

				// create a new Color object using the average colour as the red, green and blue
				// colour values
				Color avg = new Color(averageColor, averageColor, averageColor);
				
				// set the pixel at that position to the new Color object using Color.getRGB().
				image.setRGB(w, h, avg.getRGB());
			}
		}
                int find=filename.indexOf(".jpg");
                String name=filename.substring(0,find) ;
                
                        
		// save the newly created image in a new file.
                String path ="images/"+filename+"/"+name+"grey.jpg";
                
		ImageIO.write(image, "jpg", new File(path));
                return path;

	}

	public static void main(String[] args) throws IOException 
	{
            getGrayImage("image002.jpg");
		
        }    
}