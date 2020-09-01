package SvmMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class checkingFileinput {

	public static void main(String[] args) throws IOException {

		
		int countof1=0;
		String trainedfilename=HashMapfileSizecalc.pathfortrainingdataCSV+"test.csv";
		File file = new File(trainedfilename); 
		
		String testedfilename=HashMapfileSizecalc.pathfortestingdataCSV+"test.csv";
		File file1 = new File(testedfilename); 
		  
		  BufferedReader br1 = new BufferedReader(new FileReader(file1)); 
		
		
		  
		  String v1,v2; 
		  int k=0;
		  
		  while( (v2 = br1.readLine()) != null)
		  {
				
				 int firstCommaindex1=v2.indexOf(",")+1;
					int lastindexofcomma1=v2.lastIndexOf(",");
					String opoflabel1=v2.substring(firstCommaindex1, lastindexofcomma1);
					
					String featureString1[] = v2.substring(v2.indexOf(",") +3).trim().split(" ");
				
			  
			  double[] array2 = new double[featureString1.length];
				 for (int i = 0; i<featureString1.length; i++) 
					 {
					 if(!"".equals(featureString1[i])) {
					 array2[i] = Double.valueOf(featureString1[i]);
					 }
					 }  
				  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  while ((v1 = br.readLine()) != null ) 
		  {
		  
		  int firstCommaindex=v1.indexOf(",")+1;
			int lastindexofcomma=v1.lastIndexOf(",");
			String opoflabel=v1.substring(firstCommaindex, lastindexofcomma);
			
			String featureString[] = v1.substring(v1.indexOf(",") +3).trim().split(" ");
			
			
		
//			System.out.println(opoflabel);
//			System.out.println(featureString);
//			
//			System.out.println(opoflabel1);
//			System.out.println(featureString1);
//			
//			 System.out.println(v1); 
			 
			 
			 double[] array1 = new double[featureString.length];
			 for (int i = 0; i<featureString.length; i++) 
				 {
//				 System.out.println(i);
				 if(!"".equals(featureString[i])) {
				 array1[i] = Double.valueOf(featureString[i]);
				 }
				 }
			 
			 
			
			 
			 
			 test22 test = new test22();
			 
		
			 
			double dist3=test.cosineSimilarity(array1,array2);
			 
			if(dist3>0.99)
			{
				
				if(HashMapfileSizecalc.valueCalc.containsKey(Integer.parseInt(opoflabel1)))
				{
					int valkey=HashMapfileSizecalc.valueCalc.get(Integer.parseInt(opoflabel1))+1;
					
					HashMapfileSizecalc.valueCalc.put(Integer.parseInt(opoflabel1),valkey);
				}
				else
				{
					HashMapfileSizecalc.valueCalc.put(Integer.parseInt(opoflabel1),1);
					
				}
				countof1++;
			}
			 
		k++;
			 
			System.out.println(k+" "+dist3);
		  }
		  } 
System.out.println("count obtained is "+HashMapfileSizecalc.valueCalc);

Map.Entry<Integer, Integer> maxEntry = null;

for (Map.Entry<Integer, Integer> entry : HashMapfileSizecalc.valueCalc.entrySet())
{
    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
    {
        maxEntry = entry;
    }
}
System.out.println("predicted is "+maxEntry.getKey());
if(countof1>10)
{
	System.out.println();
}
	}

}
