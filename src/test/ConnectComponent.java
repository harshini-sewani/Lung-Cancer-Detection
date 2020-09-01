/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author admin
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class ConnectComponent
{
    final static int MAX_LABELS= 80000;
    static int next_label = 1;

  
    public static int[] compactLabeling(int[] image, Dimension d, boolean zeroAsBg)
    {
        //label first
        int[] label= labeling(image,d,zeroAsBg);
        int[] stat= new int[next_label+1];
        for (int i=0;i<image.length;i++) {
            if (label[i]>next_label)
                System.err.println("bigger label than next_label found!");
            stat[label[i]]++;
        }

        stat[0]=0;              // label 0 will be mapped to 0
                                // whether 0 is background or not
        int j = 1;
        for (int i=1; i<stat.length; i++) {
            if (stat[i]!=0) stat[i]=j++;
        }

        System.out.println("From "+next_label+" to "+(j-1)+" regions");
        next_label= j-1;
        for (int i=0;i<image.length;i++) label[i]= stat[label[i]];
        return label;
    }

    /**
     * return the max label in the labeling process.
     * the range of labels is [0..max_label]
     */
    public  int getMaxLabel() {return next_label;}


    /**
     * Label the connect components
     * If label 0 is background, then label 0 is untouched;
     * If not, label 0 may be reassigned
     * [Requires]
     *   0 is treated as background
     * @param image data
     * @param d dimension of the data
     * @param zeroAsBg label 0 is treated as background, so be ignored
     */
    public static int[]  labeling(int[] image, Dimension d, boolean zeroAsBg)    
    {
        int w= d.width, h= d.height;
        int[] rst= new int[w*h];
        int[] parent= new int[MAX_LABELS];
        int[] labels= new int[MAX_LABELS];
        // region label starts from 1;
        // this is required as union-find data structure
        int next_region = 1;
        for (int y = 0; y < h; ++y ){
            for (int x = 0; x < w; ++x ){
                if (image[y*w+x] == 0 && zeroAsBg) continue;
                int k = 0;
                boolean connected = false;
                // if connected to the left
                if (x > 0 && image[y*w+x-1] == image[y*w+x]) {
                   k = rst[y*w+x-1];
                   connected = true;
                }
                // if connected to the up
                if (y > 0 && image[(y-1)*w+x]== image[y*w+x] &&
                    (connected = false || image[(y-1)*w+x] < k )) {
                    k = rst[(y-1)*w+x];
                    connected = true;
                 }
                if ( !connected ) {
                    k = next_region;
                    next_region++;
                }

                if ( k >= MAX_LABELS ){
                    System.err.println("maximum number of labels reached. " +
                        "increase MAX_LABELS and recompile." );
                    System.exit(1);
                }
                rst[y*w+x]= k;
                // if connected, but with different label, then do union
                if ( x> 0 && image[y*w+x-1]== image[y*w+x] && rst[y*w+x-1]!= k )
                        uf_union( k, rst[y*w+x-1], parent );
                if ( y> 0 && image[(y-1)*w+x]== image[y*w+x] && rst[(y-1)*w+x]!= k )
                        uf_union( k, rst[(y-1)*w+x], parent );
            }
            count++;
        }

        // Begin the second pass.  Assign the new labels
        // if 0 is reserved for background, then the first available label is 1
        next_label = 1;
        for (int i = 0; i < w*h; i++ ) {
            if (image[i]!=0 || !zeroAsBg) {           
                rst[i] = uf_find( rst[i], parent, labels );                
                // The labels are from 1, if label 0 should be considered, then
                // all the label should minus 1
                if (!zeroAsBg) rst[i]--;
            }
        }
        next_label--;   // next_label records the max label
        if (!zeroAsBg) next_label--;

        System.out.println(next_label+" regions");

        return rst;
    }
   static  void uf_union( int x, int y, int[] parent)
    {
        while ( parent[x]>0 )
            x = parent[x];
        while ( parent[y]>0 )
            y = parent[y];
        if ( x != y ) {
            if (x<y)
                parent[x] = y;
            else parent[y] = x;
        }
    }

    /**
     * This function is called to return the root label
     * Returned label starts from 1 because label array is inited to 0 as first
     * [Effects]
     *   label array records the new label for every root
     */
  static  int uf_find( int x, int[] parent, int[] label)

     {
        while ( parent[x]>0 )
            x = parent[x];
        if ( label[x] == 0 )
            label[x] = next_label++;
        return label[x];
    }
  static int count=0;
    public static void main(String[] args) {
        try {
            BufferedImage image2;
            String filename;
            int[] image;
            Dimension d;
            boolean zeroAsBg=true;
            filename="malaria.jpg";
             int find=filename.indexOf(".jpg");
                    
                    String name=filename.substring(0,find) ;
             File file =new File("C:\\Documents and Settings\\admin\\My Documents\\rahul\\Java file\\Traffic Congestion Control\\images\\");
            BufferedImage image1=ImageIO.read(file);
    
            
            
            
    int w = image1.getWidth(null); 
    int h = image1.getHeight(null); 
            System.out.println("w "+w+ " h"+h);
             d = new Dimension(w,h);
//--------------------------------------------------------------------------
              int[] startPixels = new int[w*h];
        image1.getRGB(0, 0, w, h, startPixels, 0, w);
            image=startPixels;
            int[] c= compactLabeling(image, d, zeroAsBg);
            System.out.println("count "+count);
//             image2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//        WritableRaster raster = (WritableRaster) image2.getData();
//        raster.setPixels(0,0,w,h,image);
//         for(int i=0;i<c.length;i++)
//         {
//             System.out.println("c : "+c[i]);
//         }
            
                
                        
		// save the newly created image in a new file.
                String path ="images/"+filename+"/"+name+"detected.jpg";
        File fil = new File(path);
            ImageIO.write(image1, "jpg", fil);
        
            
    
            
            //---------------------------------------------------------------------------------
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
