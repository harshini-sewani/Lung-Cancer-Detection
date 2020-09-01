/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knn;

/**
 *
 * @author Ningesh
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Knn {
	public static class Sample {
		public String label;
		public double [] pixels;
	}
	
	public static List<Sample> readFile(String file) throws IOException {
		List<Sample> samples = new ArrayList<Sample>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try {
			String line = reader.readLine(); // ignore header
			while((line = reader.readLine()) != null) {
				String[] tokens = line.split(",");
				Sample sample = new Sample();
				sample.label = tokens[0];
				sample.pixels = new double[tokens.length - 1];
				for(int i = 1; i < tokens.length; i++) {
					sample.pixels[i-1] = Double.parseDouble(tokens[i]);
				}
				samples.add(sample);
			}	
		} finally { reader.close(); }
		return samples;
	}
	
	public static double distance(double[] a, double[] b) {
		int sum = 0;
		for(int i = 0; i < a.length; i++) {
			sum += (a[i] - b[i]) * (a[i] - b[i]);
		}
		return (double)Math.sqrt(sum); // euclidian distance would be sqrt(sum)...
	}
	
	public static String classify(List<Sample> trainingSet, double[] pixels) {
		String label = "";
		double bestDistance = Integer.MAX_VALUE;
		for(Sample sample: trainingSet) {
			double dist = distance(sample.pixels, pixels);
			if(dist < bestDistance) {
				bestDistance = dist;
				label = sample.label;
			}
		}
		return label;
	}
	
	public static void main(String[] argv) throws IOException {
		List<Sample> trainingSet = readFile("trained.csv");
		List<Sample> validationSet = readFile("test.csv");
		int numCorrect = 0;
                 Map<String, Integer> freq = new HashMap<String, Integer>();
		for(Sample sample:validationSet) {
                    
                    String outputobatined=classify(trainingSet, sample.pixels);
                    System.out.println(numCorrect+++"Classified into "+outputobatined);
                    String word=""+outputobatined;
                   
                    int count = freq.containsKey(word) ? freq.get(word) : 0;
                    freq.put(word, count + 1);
                    
//			if(classify(trainingSet, sample.pixels) == sample.label) numCorrect++;
		}
                
                System.out.println(freq);
                
                 int maxValueInMap=(Collections.max(freq.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                System.out.println("max value is "+entry.getKey());   
                
                if(entry.getKey().equals("0.0"))
                {
                 System.out.println("User is classified into  parkisnsons disease");  
                }
                else
                {
                System.out.println("User does not have any disease");  
                }

                
// Print the key with max value
            }
        }
//		System.out.println("Accuracy: " + (double)numCorrect / validationSet.size() * 100 + "%");
	}
        
        public static String outPutPrediction()
        {
          String op="";
        
            try {
                List<Sample> trainingSet = readFile("trained.csv");
                List<Sample> validationSet = readFile("test.csv");
                int numCorrect = 0;
                Map<String, Integer> freq = new HashMap<String, Integer>();
                for(Sample sample:validationSet) {
                    
                    String outputobatined=classify(trainingSet, sample.pixels);
                    System.out.println(numCorrect+++"Classified into "+outputobatined);
                    String word=""+outputobatined;
                   
                    int count = freq.containsKey(word) ? freq.get(word) : 0;
                    freq.put(word, count + 1);
                    
//			if(classify(trainingSet, sample.pixels) == sample.label) numCorrect++;
                }
                
                System.out.println(freq);
              
                int maxValueInMap=(Collections.max(freq.values()));  // This will return max value in the Hashmap
                for (Map.Entry<String, Integer> entry : freq.entrySet()) {  // Itrate through hashmap
                    if (entry.getValue()==maxValueInMap) {
                        System.out.println("max value is "+entry.getKey());
                        
//                        if(entry.getKey().equals("1.0"))
//                        {
//                            op="User is classified into  parkisnsons disease";
//                            System.out.println("User is classified into  parkisnsons disease");  
//                        }
//                        else
//                        {
//                            op="User does not have any disease";
//                            System.out.println("User does not have any disease");
//                        }
                        op=entry.getKey();
                        
// Print the key with max value
                    }
                }
                
               
            } catch (IOException ex) {
                Logger.getLogger(Knn.class.getName()).log(Level.SEVERE, null, ex);
            }
         return op;
        }
}