package SvmMain;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class SVM {

public static void main(String[] args) {

// Create Java spark context
args=new String[3];
args[0]="F:\\maven\\SvmForImageClassification\\trainedcsv";// folder contains the training csv file we have generated in imageparser Program 1
args[1]="F:\\maven\\SvmForImageClassification\\testdata";//  folder contains the testing image csv files
args[2]="1";//100 – is number of iterations to perform without reaching convergence.
	
//SparkConf conf = new SparkConf().setAppName("SVM vs Navie Bayes");

SparkConf conf = new SparkConf()
.setAppName("SVM vs Navie Bayes").setMaster("local[2]").set("spark.executor.memory","1g");
JavaSparkContext sc = new JavaSparkContext(conf);

//SparkConf sc = new SparkConf().setAppName("SOME APP NAME").setMaster("local[2]").set("spark.executor.memory","1g");

//SparkSession spark = SparkSession
//.builder()
//.appName("Java Spark SQL basic example")
//.config("spark.master", "local")
//.getOrCreate();

//RDD training = MLUtils.loadLabeledData(sc, args[0]);
//RDD test = MLUtils.loadLabeledData(sc, args[1]); // test set

JavaRDD training = sc.textFile(args[0]).cache().map(new Function<String,LabeledPoint> () {

@Override
public LabeledPoint call(String v1) throws Exception {
	double label = 0;
	double[] v = null;
	System.out.println("input labelled "+v1);
	if(!v1.trim().equals("")) {
	int firstCommaindex=v1.indexOf(",")+1;
	int lastindexofcomma=v1.lastIndexOf(",");
	String opoflabel=v1.substring(firstCommaindex, lastindexofcomma);
	System.out.println(firstCommaindex+" "+lastindexofcomma+" "+opoflabel);
	if(!opoflabel.trim().equals("")) {
 label = Double.parseDouble(opoflabel);
String featureString[] = v1.substring(v1.indexOf(",") +3).trim().split(" ");
 v = new double[featureString.length];
int i = 0;
for (String s : featureString) {
if (s.trim().equals(""))
continue;
v[i++] = Double.parseDouble(s.trim());
}
	}
	}
return new LabeledPoint(label, Vectors.dense(v));
}


});
System.out.println(training.count());
JavaRDD test = sc.textFile(args[1]).cache().map(new Function<String,LabeledPoint> () {

@Override
public LabeledPoint call(String v1) throws Exception {
	double label =0;
	double[] v = null;
	int firstCommaindex=v1.indexOf(",")+1;
	int lastindexofcomma=v1.lastIndexOf(",");
	String opoflabel=v1.substring(firstCommaindex, lastindexofcomma);
	if(!opoflabel.trim().equals("")) {
 label = Double.parseDouble(opoflabel);
String featureString[] = v1.substring(v1.indexOf(",") + 3).trim().split(" ");
 v = new double[featureString.length];
int i = 0;
for (String s : featureString) {
if (s.trim().equals(""))
continue;
v[i++] = Double.parseDouble(s.trim());
}
	}
return new LabeledPoint(label, Vectors.dense(v));
}

});
System.out.println(test.count());
//final NaiveBayesModel model = NaiveBayes.train(training.rdd(), 1.0);

//JavaPairRDD<Double, Double> predictionAndLabel = test.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
//@Override
//public Tuple2<Double, Double> call(LabeledPoint p) {
//return new Tuple2<Double, Double>(model.predict(p
//.features()), p.label());
//}
//});
//double accuracy = 1.0
//* predictionAndLabel.filter(
//new Function<Tuple2<Double, Double>, Boolean>() {
//@Override
//public Boolean call(Tuple2<Double, Double> pl) {
////System.out.println(pl._1() + " -- " + pl._2());
//return pl._1().intValue() == pl._2().intValue();
//}
//}).count() / (double)test.count();
//System.out.println("navie bayes accuracy : " + accuracy);
//
final SVMModel svmModel = SVMWithSGD.train(training.rdd(), Integer.parseInt(args[2]));

JavaPairRDD<Double, Double> predictionAndLabelSVM = test.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
@Override
public Tuple2<Double, Double> call(LabeledPoint p) {
return new Tuple2<Double, Double>(svmModel.predict(p
.features()), p.label());
}
});
double accuracySVM = 1.0
* predictionAndLabelSVM.filter(
new Function<Tuple2<Double, Double>, Boolean>() {
@Override
public Boolean call(Tuple2<Double, Double> pl) {
System.out.println(pl._1() + " predicted " + pl._2());
if(HashMapfileSizecalc.sizeCalc.containsKey(pl._2())) {
int val=HashMapfileSizecalc.sizeCalc.get(pl._2())+1;
HashMapfileSizecalc.sizeCalc.put(pl._2(), val);	
}
else
{
	HashMapfileSizecalc.sizeCalc.put(pl._2(), 1);	
}
return pl._1().intValue() == pl._2().intValue();
}
}).count() / (double)test.count();
System.out.println("svm accuracy : " + accuracySVM);
System.out.println("hasmap obtained is "+HashMapfileSizecalc.sizeCalc);

}

public static HashMap<Double, Integer> PredictionUsingSVM(String traindatacsvpath,String testdatacsvpath,String iterations)
{
	


	// Create Java spark context
	String args[]=new String[3];
	args[0]=traindatacsvpath;// folder contains the training csv file we have generated in imageparser Program 1
	args[1]=testdatacsvpath;//  folder contains the testing image csv files
	args[2]=iterations;//100 – is number of iterations to perform without reaching convergence.
		
	//SparkConf conf = new SparkConf().setAppName("SVM vs Navie Bayes");

	SparkConf conf = new SparkConf()
	.setAppName("SVM vs Navie Bayes").setMaster("local[2]").set("spark.executor.memory","1g");
	JavaSparkContext sc = new JavaSparkContext(conf);

	//SparkConf sc = new SparkConf().setAppName("SOME APP NAME").setMaster("local[2]").set("spark.executor.memory","1g");

	//SparkSession spark = SparkSession
	//.builder()
	//.appName("Java Spark SQL basic example")
	//.config("spark.master", "local")
	//.getOrCreate();

	//RDD training = MLUtils.loadLabeledData(sc, args[0]);
	//RDD test = MLUtils.loadLabeledData(sc, args[1]); // test set

	JavaRDD training = sc.textFile(args[0]).cache().map(new Function<String,LabeledPoint> () {

	@Override
	public LabeledPoint call(String v1) throws Exception {
		double label = 0;
		double[] v = null;
		System.out.println("input labelled "+v1);
		if(!v1.trim().equals("")) {
		int firstCommaindex=v1.indexOf(",")+1;
		int lastindexofcomma=v1.lastIndexOf(",");
		String opoflabel=v1.substring(firstCommaindex, lastindexofcomma);
		System.out.println(firstCommaindex+" "+lastindexofcomma+" "+opoflabel);
		if(!opoflabel.trim().equals("")) {
	 label = Double.parseDouble(opoflabel);
	String featureString[] = v1.substring(v1.indexOf(",") +3).trim().split(" ");
	 v = new double[featureString.length];
	int i = 0;
	for (String s : featureString) {
	if (s.trim().equals(""))
	continue;
	v[i++] = Double.parseDouble(s.trim());
	}
		}
		}
	return new LabeledPoint(label, Vectors.dense(v));
	}


	});
	System.out.println(training.count());
	JavaRDD test = sc.textFile(args[1]).cache().map(new Function<String,LabeledPoint> () {

	@Override
	public LabeledPoint call(String v1) throws Exception {
		double label =0;
		double[] v = null;
		int firstCommaindex=v1.indexOf(",")+1;
		int lastindexofcomma=v1.lastIndexOf(",");
		String opoflabel=v1.substring(firstCommaindex, lastindexofcomma);
		if(!opoflabel.trim().equals("")) {
	 label = Double.parseDouble(opoflabel);
	String featureString[] = v1.substring(v1.indexOf(",") + 3).trim().split(" ");
	 v = new double[featureString.length];
	int i = 0;
	for (String s : featureString) {
	if (s.trim().equals(""))
	continue;
	v[i++] = Double.parseDouble(s.trim());
	}
		}
	return new LabeledPoint(label, Vectors.dense(v));
	}

	});
	System.out.println(test.count());
	//final NaiveBayesModel model = NaiveBayes.train(training.rdd(), 1.0);

	//JavaPairRDD<Double, Double> predictionAndLabel = test.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
	//@Override
	//public Tuple2<Double, Double> call(LabeledPoint p) {
	//return new Tuple2<Double, Double>(model.predict(p
	//.features()), p.label());
	//}
	//});
	//double accuracy = 1.0
	//* predictionAndLabel.filter(
	//new Function<Tuple2<Double, Double>, Boolean>() {
	//@Override
	//public Boolean call(Tuple2<Double, Double> pl) {
	////System.out.println(pl._1() + " -- " + pl._2());
	//return pl._1().intValue() == pl._2().intValue();
	//}
	//}).count() / (double)test.count();
	//System.out.println("navie bayes accuracy : " + accuracy);
	//
	final SVMModel svmModel = SVMWithSGD.train(training.rdd(), Integer.parseInt(args[2]));

	JavaPairRDD<Double, Double> predictionAndLabelSVM = test.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
	@Override
	public Tuple2<Double, Double> call(LabeledPoint p) {
	return new Tuple2<Double, Double>(svmModel.predict(p
	.features()), p.label());
	}
	});
	double accuracySVM = 1.0
	* predictionAndLabelSVM.filter(
	new Function<Tuple2<Double, Double>, Boolean>() {
	@Override
	public Boolean call(Tuple2<Double, Double> pl) {
	System.out.println(pl._1() + " predicted " + pl._2());
	if(HashMapfileSizecalc.sizeCalc.containsKey(pl._1())) {
	int val=HashMapfileSizecalc.sizeCalc.get(pl._1())+1;
	HashMapfileSizecalc.sizeCalc.put(pl._1(), val);	
	}
	else
	{
		HashMapfileSizecalc.sizeCalc.put(pl._1(), 1);	
	}
	return pl._1().intValue() == pl._1().intValue();
	}
	}).count() / (double)test.count();
	System.out.println("svm accuracy : " + accuracySVM);
	System.out.println("hasmap obtained is "+HashMapfileSizecalc.sizeCalc);

	
	
	
	
	return HashMapfileSizecalc.sizeCalc;
	


}
}