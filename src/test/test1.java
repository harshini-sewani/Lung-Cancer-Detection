
package test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class test1
{
    static String filename="malaria.jpg";
    
    



    public static BufferedImage convert(BufferedImage imgJPEG) {
                // Create a new Binary Buffer
        System.out.println("img "+imgJPEG.getWidth()+" "+ imgJPEG.getHeight()+" "+ BufferedImage.TYPE_BYTE_BINARY);
                BufferedImage img = new BufferedImage(imgJPEG.getWidth(), imgJPEG.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
                WritableRaster raster = img.getRaster();
                WritableRaster rasterPB = imgJPEG.getRaster();
                // Foreach pixel  check if the new one must be white or black 
                for(int h=0;h<256;h++)
                        for(int w=0;w<256;w++) {
                                int[] p = new int[1];
                                rasterPB.getPixel(w, h, p);
                                if(p[0] > 127) { 
                                        raster.setSample(w, h, 0, 1);
                                } else { 
                                        raster.setSample(w, h, 0, 0);
                                }
                        }
                return img;
        }
    
    public static void main(String[] args) {
        try {
            File file =new File("images/"+filename+"/"+"malaria.jpg");
            System.out.println("fie "+file.getAbsolutePath());
            BufferedImage image = ImageIO.read(file);
            BufferedImage image1=convert(image);       
             int find=filename.indexOf(".");
                String name=filename.substring(0,find) ;
                
                        
		// save the newly created image in a new file.
                String path ="images/"+filename+"/"+name+"binary.jpg";
                System.out.println("path "+path);
		ImageIO.write(image1, "jpg", new File(path));
                   
                 
        }
        //   public static String toBinary(byte b) {
        //        StringBuilder sb = new StringBuilder("00000000");
        //
        //        for (int bit = 0; bit < 8; bit++) {
        //            if (((b >> bit) & 1) > 0) {
        //                sb.setCharAt(7 - bit, '1');
        //            }
        //        }
        //         return (sb.toString());
        //   }
        //    public static void main(String[] args)  {
        //        try {
        //
        //             String path="images/"+filename+"/";
        //             File file =new File("images/"+filename+"/"+"malariagrey.jpg");
        //             BufferedImage image = ImageIO.read(file);
        //
        //    // write it to byte array in-memory (jpg format)
        //    ByteArrayOutputStream b = new ByteArrayOutputStream();
        //    ImageIO.write(image, "jpg", b);
        //
        //    // do whatever with the array...
        //    byte[] jpgByteArray = b.toByteArray();
        //
        //    // convert it to a String with 0s and 1s
        //    StringBuilder sb = new StringBuilder();
        //    for (byte by : jpgByteArray)
        //        sb.append(Integer.toBinaryString(by & 0xFF));
        //
        //    System.out.println(sb.toString());
        //
        //                       ImageIO.write(image, "jpg", f);
        //
        ////            byte[] data = null;
        ////                    ByteArrayOutputStream bas = null;
        ////
        ////                    String path="images/"+filename+"/";
        ////                        File file =new File("images/"+filename+"/"+"malariagrey.jpg");
        ////
        ////
        ////
        ////                        BufferedImage imgToServe = ImageIO.read(file);;
        ////
        ////
        ////
        ////                        bas = new ByteArrayOutputStream();
        ////                        ImageIO.write(imgToServe, "jpg", bas);
        ////
        ////
        ////                        File f = new File(path+"malariabinary.jpg");
        ////                        ImageIO.write(imgToServe, "jpg", f);
        ////
        ////                        data = bas.toByteArray();
        ////                        String str = "";
        ////                        for (int i = 0; i < data.length; i++) {
        ////                            str = str + toBinary(data[i]);
        ////                        }
        ////                        System.out.println(str);
        //        } catch (IOException ex) {
        //            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        //
        //        }
        catch (IOException ex) {
            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    
    
    
//   public static String toBinary(byte b) {
//        StringBuilder sb = new StringBuilder("00000000");
//
//        for (int bit = 0; bit < 8; bit++) {
//            if (((b >> bit) & 1) > 0) {
//                sb.setCharAt(7 - bit, '1');
//            }
//        }
//         return (sb.toString());
//   }
//    public static void main(String[] args)  {
//        try {
//            
//             String path="images/"+filename+"/";
//             File file =new File("images/"+filename+"/"+"malariagrey.jpg");
//             BufferedImage image = ImageIO.read(file);
//
//    // write it to byte array in-memory (jpg format)
//    ByteArrayOutputStream b = new ByteArrayOutputStream();
//    ImageIO.write(image, "jpg", b);
//
//    // do whatever with the array...
//    byte[] jpgByteArray = b.toByteArray();
//
//    // convert it to a String with 0s and 1s        
//    StringBuilder sb = new StringBuilder();
//    for (byte by : jpgByteArray)
//        sb.append(Integer.toBinaryString(by & 0xFF));
//
//    System.out.println(sb.toString());
//   
//                       ImageIO.write(image, "jpg", f);
//            
////            byte[] data = null;
////                    ByteArrayOutputStream bas = null;
////                
////                    String path="images/"+filename+"/";
////                        File file =new File("images/"+filename+"/"+"malariagrey.jpg");
////                 
////               
////                       
////                        BufferedImage imgToServe = ImageIO.read(file);;
////              
////                           
////                        
////                        bas = new ByteArrayOutputStream();
////                        ImageIO.write(imgToServe, "jpg", bas);
////                        
////                        
////                        File f = new File(path+"malariabinary.jpg");
////                        ImageIO.write(imgToServe, "jpg", f);
////
////                        data = bas.toByteArray();
////                        String str = "";
////                        for (int i = 0; i < data.length; i++) {
////                            str = str + toBinary(data[i]);
////                        }
////                        System.out.println(str);
//        } catch (IOException ex) {
//            Logger.getLogger(test1.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        } 
    }

 

   
