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


import java.io.File;
import java.io.FileFilter;

import java.io.*; 
public class MyFileFilter implements FilenameFilter { 
String ext; 
public MyFileFilter(String ext) { 
this.ext = "." + ext; 
} 
public boolean accept(File dir, String name) { 
return name.endsWith(ext); 
} 
}
