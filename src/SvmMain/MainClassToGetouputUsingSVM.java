package SvmMain;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class MainClassToGetouputUsingSVM {
public static void main(String args[]) throws IOException
{
	
	
	// for testing dataset creation
//	FileUtils.cleanDirectory(HashMapfileSizecalc.pathfortestingdataCSV); 
	
	
	

	//now directory is empty, so we can delete it
//	System.out.println("Deleting Directory. Success = "+dir.delete());
	
	
	 String path=HashMapfileSizecalc.pathfortestingdataCSV; 
     File file = new File(path);
     File[] files = file.listFiles(); 
     for (File f:files) 
     {if (f.isFile() && f.exists()) 
         { f.delete();
System.out.println("successfully deleted");
         }else{
System.out.println("cant delete a file due to open or error");
} } 
	
	CreatingTestDataofImage ctdi=new CreatingTestDataofImage();
	String foldertobeclassfiedtest="species3";
	String trainingdatasetpathtest=HashMapfileSizecalc.pathfortestingdataImages+foldertobeclassfiedtest;
	
	
	String classfiedintotest="0";// depending on the input category classify 0-female 1-female
	ctdi.creatingTestingDatasetForImage(trainingdatasetpathtest, classfiedintotest);
	
 
	// for outputcreationofdata
	
	HashMap<Double, Integer> sizeCalc=new HashMap<>();
	String iterationCounts="1";
	sizeCalc=SVM.PredictionUsingSVM(HashMapfileSizecalc.pathfortrainingdataCSV, HashMapfileSizecalc.pathfortestingdataCSV, iterationCounts);

	  Map.Entry<Double, Integer> maxEntry = null;

	  for (Map.Entry<Double, Integer> entry : sizeCalc.entrySet())
	  {
	      if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
	      {
	          maxEntry = entry;
	      }
	  }
	
	  
	  String outputPredictedis=maxEntry.getKey().toString();
	  System.out.println("predictedoutput is "+outputPredictedis);

}
}
