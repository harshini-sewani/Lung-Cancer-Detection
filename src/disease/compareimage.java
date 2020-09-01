package disease;

import java.io.File;
import java.io.IOException;
import java.util.List;

import admin.path;

public class compareimage {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 List<String> list;
		 String imagepath="F:\\2016 2017 project backup\\001 pesticide management and prediction of crops and diseases related to it\\CropDiseasePrediction\\WebContent\\thumbs\\Cotton\\CottonRust02.jpg";
		   
		 String folderpath="F:\\2016 2017 project backup\\001 pesticide management and prediction of crops and diseases related to it\\CropDiseasePrediction\\WebContent\\thumbs\\Cotton";
		   
		 list = FeatureExtraction.main(imagepath, new File(folderpath));
		 
		 System.out.println(list);
	}

}
