/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author admin
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.FileUtils;

public class CopyFile {
 
public static void copyFile(File afile,File bfile)
{
    	try{
            String apath=afile.getParent();
            String bpath=bfile.getAbsolutePath();
            
            if(!(apath.equals(bpath)))
            {
                FileUtils.copyFileToDirectory(afile, bfile);
            }
            
            
    	    System.out.println("File is copied successful!");
 
    	}catch(IOException e){
    	    e.printStackTrace();
    	}
}
    
    

}
