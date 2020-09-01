package SvmMain;

import java.io.IOException;

public class TrainingDatasetimages {
	
	public static void main(String args[]) throws IOException {
	// training dataset creation
	
		ImageParser imp=new ImageParser();
		String foldertobeclassfied="species3";
		String classfiedinto="1";// depending on the input category classify 0-female 1-female
		String trainingdatasetpath=HashMapfileSizecalc.pathfortrainingdataImages+foldertobeclassfied;

		imp.creatingTrainingDatasetForImage(trainingdatasetpath, classfiedinto);
	}
	}
