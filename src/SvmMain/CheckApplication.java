package SvmMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CheckApplication {
	


	public static void main(String[] args) throws IOException {

		 
		int countof1=0;
		String trainedfilename="trained.csv";
		File file = new File(trainedfilename); 
		
		String testedfilename="test.csv";
		File file1 = new File(testedfilename); 
		  
		  BufferedReader br1 = new BufferedReader(new FileReader(file1)); 
		 
		  String v1,v2; 
		  int k=0;
		
		  while( (v2 = br1.readLine()) != null)
		  {
				
				 int firstCommaindex1=v2.indexOf(",")+1;
					int lastindexofcomma1=v2.lastIndexOf(",");
//					String opoflabel1=v2.substring(0, firstCommaindex1);//v2.substring(firstCommaindex1, lastindexofcomma1);
					
					String imagename=v2.substring(0, firstCommaindex1);
					
					String featureString1[] = v2.substring(firstCommaindex1, lastindexofcomma1).split(",");
				
			  
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
			  String opoflabel1="0";
		  int firstCommaindex=v1.indexOf(",")+1;
			int lastindexofcomma=v1.lastIndexOf(",");
			 opoflabel1=v1.substring(0, firstCommaindex);;
			
			String featureString[] =v1.substring(firstCommaindex, lastindexofcomma).split(",");
			
			
		
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
			 
			if(dist3>0.985)
			{
				
				
				if(HashMapfileSizecalc.valueCalcOption.containsKey(opoflabel1))
				{
					int valkey=HashMapfileSizecalc.valueCalcOption.get(opoflabel1)+1;
					
					HashMapfileSizecalc.valueCalcOption.put(opoflabel1,valkey);
				}
				else
				{
					HashMapfileSizecalc.valueCalcOption.put(opoflabel1,1);
					
				}
				countof1++;
			}
			 
		k++;
			 
//			System.out.println(k+" "+dist3);
		System.out.println(k);
		  }
		  } 
		  
		  br1.close();
System.out.println("count obtained is "+HashMapfileSizecalc.valueCalcOption);

Map.Entry<String, Integer> maxEntry = null;

for (Map.Entry<String, Integer> entry : HashMapfileSizecalc.valueCalcOption.entrySet())
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
