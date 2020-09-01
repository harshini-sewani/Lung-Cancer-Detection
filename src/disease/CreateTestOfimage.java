package disease;

import java.io.File;
import java.io.IOException;

public class CreateTestOfimage {
	public static void main(String[] args) throws IOException {
		
		
		 String folderpath="F:\\2016 2017 project backup\\001 pesticide management and prediction of crops and diseases related to it\\CropDiseasePrediction\\WebContent\\thumbs\\test";
		   
		// TODO Auto-generated method stub
		FeatureExtraction.generateCSVoffolder(new File(folderpath),"test");
	}
}
