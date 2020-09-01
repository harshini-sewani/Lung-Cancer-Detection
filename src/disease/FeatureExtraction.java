package disease;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.feature.local.matcher.FastBasicKeypointMatcher;
import org.openimaj.feature.local.matcher.LocalFeatureMatcher;
import org.openimaj.feature.local.matcher.consistent.ConsistentLocalFeatureMatcher2d;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.feature.local.engine.asift.ASIFTEngine;
import org.openimaj.image.feature.local.keypoints.Keypoint;
import org.openimaj.math.geometry.transforms.HomographyRefinement;
import org.openimaj.math.geometry.transforms.estimation.RobustHomographyEstimator;
import org.openimaj.math.model.fit.RANSAC;
import org.openimaj.util.pair.Pair;

import SvmMain.HashMapfileSizecalc;
import utils.ImageFileFilter;


/**
 * Example showing how to extract ASIFT features and match them.
 *
 * @author Jonathon Hare (jsh2@ecs.soton.ac.uk)
 * @author Sina Samangooei (ss@ecs.soton.ac.uk)
 *
 */
public class FeatureExtraction {

    public static List<String> main(String input, File folder) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        // Read the images from two streams
        File file1 = new File(input);
        System.out.println(file1.toString());
        final FImage input_1 = ImageUtilities.readF(file1);

       
            
           File  file = new File("test.csv");
    if(file.delete()){
        System.out.println("file.txt File deleted from Project root directory");
    }else System.out.println("File file.txt doesn't exists in project root directory");
    
    
    StringBuilder sb = new StringBuilder();
    BufferedWriter br = null;
    br = new BufferedWriter(new FileWriter("test.csv"));
  
  
        // Prepare the engine to the parameters in the IPOL demo
        final ASIFTEngine engine = new ASIFTEngine(false, 7);

        // Extract the keypoints from both images
        final LocalFeatureList<Keypoint> input1Feats = engine.findKeypoints(input_1);
//        System.out.println("Extracted input1: " + input1Feats.size());
        
        for(Keypoint kp:input1Feats) {
        	
        	
        	
        	System.out.println(kp.getX()+" "+kp.getY()+" "+kp.getScale()+" "+kp.asciiHeader()+" "+kp.hashCode()+" "+kp.getOrdinate(0)+" "+kp.ori);
        	ByteArrayInputStream bai = new ByteArrayInputStream(kp.getFeatureVector().getVector());
    		
    		int ch;
    		sb.append(file1.getName());
    		sb.append(",");
    		sb.append(kp.ori+","+kp.getX()+","+kp.getY()+","+kp.getScale());
    		//read bytes from ByteArrayInputStream using read method
    		while((ch = bai.read()) != -1)
    		{
//    			System.out.print(ch);
    			sb.append(ch);
    			sb.append(",");
    		}
        
    		sb.append("\n");
        
        }
        br.write(sb.toString());
        br.close();
        // Prepare the matcher, uncomment this line to use a basic matcher as
        // opposed to one that enforces homographic consistency
        // LocalFeatureMatcher<Keypoint> matcher = createFastBasicMatcher();
        final LocalFeatureMatcher<Keypoint> matcher = createConsistentRANSACHomographyMatcher();

        // Find features in image 1
        matcher.setModelFeatures(input1Feats);
        
        int i = 0;
        
        File[] files = folder.listFiles(new ImageFileFilter(false));
        for (File f : files) {
            final FImage input_2 = ImageUtilities.readF(f);
            final LocalFeatureList<Keypoint> input2Feats = engine.findKeypoints(input_2);
            matcher.findMatches(input2Feats);
            final List<Pair<Keypoint>> matches = matcher.getMatches();
            System.out.println(f.toString() + "  NMatches: " + matches.size());
//           if( matches.size()>20){
            map.put(f.getName().toString(), matches.size());
//           }
        }

       // System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        List<Map.Entry<String, Integer>> greatest = findGreatest(map, files.length);
        for (Map.Entry<String, Integer> entry : greatest) {
           // System.out.println(entry);
            list.add(entry.getKey()+ "");
        }

//        for (int i = list.size() - 1; i > 0; i--) {
//            String val = list.get(i);
//            String vals[] = val.split("=");
//            System.err.println(vals[0]);
//            System.err.println(vals[1]);
//        }
//        List<Integer> lists = new ArrayList<Integer>(map.values());
//        Collections.sort(lists, Collections.reverseOrder());
//        List<Integer> top = lists.subList(0, files.length);
//        System.out.println(top);
        return list;
    }

    public static void generateCSVoffolder(File folder,String filetype) throws IOException
    {

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        // Read the images from two streams
      
    
    
    
    BufferedWriter br = null;
    br = new BufferedWriter(new FileWriter(filetype+".csv"));
  
  
        // Prepare the engine to the parameters in the IPOL demo
        final ASIFTEngine engine = new ASIFTEngine(false, 7);

      
     
        // Prepare the matcher, uncomment this line to use a basic matcher as
        // opposed to one that enforces homographic consistency
        // LocalFeatureMatcher<Keypoint> matcher = createFastBasicMatcher();
        final LocalFeatureMatcher<Keypoint> matcher = createConsistentRANSACHomographyMatcher();

        // Find features in image 1
       
        
        int i = 0;
        
        File[] files = folder.listFiles(new ImageFileFilter(false));
       
        for (File f : files) {
        	StringBuilder sb = new StringBuilder();
            final FImage input_2 = ImageUtilities.readF(f);
            final LocalFeatureList<Keypoint> input2Feats = engine.findKeypoints(input_2);
            for(Keypoint kp:input2Feats) {
            	
            	
            	
            	System.out.println(kp.getX()+" "+kp.getY()+" "+kp.getScale()+" "+kp.asciiHeader()+" "+kp.hashCode()+" "+kp.getOrdinate(0)+" "+kp.ori);
            	ByteArrayInputStream bai = new ByteArrayInputStream(kp.getFeatureVector().getVector());
        		
        		int ch;
        		sb.append(f.getName());
        		 HashMapfileSizecalc.imagenamewithvalues.put(i, f.getName());
        		sb.append(",");
        		sb.append(kp.ori+","+kp.getX()+","+kp.getY()+","+kp.getScale());
        		//read bytes from ByteArrayInputStream using read method
        		while((ch = bai.read()) != -1)
        		{
//        			System.out.print(ch);
        			sb.append(ch);
        			sb.append(",");
        		}
            
        		sb.append("\n");
            
            }
            br.write(sb.toString());
           i++;
            
        }
        br.close(); 
         

       // System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    

//        for (int i = list.size() - 1; i > 0; i--) {
//            String val = list.get(i);
//            String vals[] = val.split("=");
//            System.err.println(vals[0]);
//            System.err.println(vals[1]);
//        }
//        List<Integer> lists = new ArrayList<Integer>(map.values());
//        Collections.sort(lists, Collections.reverseOrder());
//        List<Integer> top = lists.subList(0, files.length);
//        System.out.println(top);
     
    
    }
    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>>
            findGreatest(Map<K, V> map, int n) {
        Comparator<? super Map.Entry<K, V>> comparator
                = new Comparator<Map.Entry<K, V>>() {
                    @Override
                    public int compare(Map.Entry<K, V> e0, Map.Entry<K, V> e1) {
                        V v0 = e0.getValue();
                        V v1 = e1.getValue();
                        return v0.compareTo(v1);
                    }
                };
        PriorityQueue<Map.Entry<K, V>> highest
                = new PriorityQueue<Map.Entry<K, V>>(n, comparator);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            highest.offer(entry);
            while (highest.size() > n) {
                highest.poll();
            }
        }

        List<Map.Entry<K, V>> result = new ArrayList<Map.Entry<K, V>>();
        while (highest.size() > 0) {
            result.add(highest.poll());
        }
        return result;
    }

    /**
     * @return a matcher with a homographic constraint
     */
    private static LocalFeatureMatcher<Keypoint> createConsistentRANSACHomographyMatcher() {
        final ConsistentLocalFeatureMatcher2d<Keypoint> matcher = new ConsistentLocalFeatureMatcher2d<Keypoint>(
                createFastBasicMatcher());
        matcher.setFittingModel(new RobustHomographyEstimator(10.0, 1000, new RANSAC.BestFitStoppingCondition(),
                HomographyRefinement.NONE));

        return matcher;
    }

    /**
     * @return a basic matcher
     */
    private static LocalFeatureMatcher<Keypoint> createFastBasicMatcher() {
        return new FastBasicKeypointMatcher<Keypoint>(8);
    }
}
