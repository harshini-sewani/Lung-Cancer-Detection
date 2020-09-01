package disease;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.feature.local.keypoints.Keypoint;
import org.openimaj.util.pair.Pair;

import utils.ImageFileFilter;

public class TrainFolder {

	public static void main(String[] args) throws IOException 
        {
            String folderpath="F:\\2016 2017 project backup\\001 pesticide management and prediction of crops and diseases related to it\\CropDiseasePrediction\\WebContent\\thumbs\\Cotton";	
            FeatureExtraction.generateCSVoffolder(new File(folderpath),"trained");
	}

}
