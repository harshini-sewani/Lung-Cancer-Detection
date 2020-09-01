/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package morphological.operations;

/**
 *
 * @author admin
 */


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Closing operation for grayscaled images. The closing operand is the
 * {@link Erosion} of the {@link Dilation}.
 * 
 * The closing operation will remove "holes", i.e. darker parts, in the image.
 * This operation can for instance be useful to remove uninteresting structures
 * yielding better accuracy when performing edge detection.
 * 
 * @author Tomas
 * 
 */
public class Closing extends AbstractOperation {

	private STRUCTURING_ELEMENT_SHAPE shape;

	public Closing() {
		shapeSize = 2;
		shape = STRUCTURING_ELEMENT_SHAPE.SQUARE;
	}

	public Closing(STRUCTURING_ELEMENT_SHAPE shape, int shapeSize) {
		this.shape = shape;
		super.shapeSize = shapeSize;
	}

	/**
	 * @see AbstractOperation
	 */
	@Override
	public BufferedImage execute(BufferedImage img) {
		if (img.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new IllegalArgumentException(
					"The image must be of type TYPE_BYTE_GRAY");

		BufferedImage dilatedImg, closedImg;
		Erosion erosion = new Erosion(shape, shapeSize);
		Dilation dilation = new Dilation(shape, shapeSize);

		dilatedImg = dilation.execute(img);
		closedImg = erosion.execute(dilatedImg);

		return closedImg;
	}
 public static void main(String[] args) {
        try {
            String filename="malariadilate.jpg";
               File file =new File("images/malaria.jpg/"+filename);
        System.out.println("fie "+file.getAbsolutePath());
                    BufferedImage image = ImageIO.read(file);
                  
                    STRUCTURING_ELEMENT_SHAPE shape=STRUCTURING_ELEMENT_SHAPE.SQUARE;
                  int shapeSize=1;
                    Dilation  d=new Dilation(shape,shapeSize);
                    
                    
                    BufferedImage image1=d.execute(image);
                     String path="images/malaria.jpg/"+"malariaclosing.jpg";
                    ImageIO.write(image1, "jpg", new File(path));
        } 
        catch (IOException ex) {
            Logger.getLogger(Closing.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
}