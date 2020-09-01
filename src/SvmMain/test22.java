package SvmMain;

public class test22{
	 
public static void main ( String [] args ) {
 
 
double [] array1= {1,2,1};
double [] array2= {1,0,1};
 
double [][] X = {{1,1,1},{1,1,1}};
 
 
test22 test = new test22();
 
//double Dist1 = test.calculateDistance( array1,array2);
// 
//double Dist2 = test.Euclideanorm(X);
 
double dist3=test.cosineSimilarity(array1,array2);
 
 
//System.out.println(Dist1);
 
//System.out.println(Dist2);
 
System.out.println(dist3);
}

public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
    double dotProduct = 0.0;
    double normA = 0.0;
    double normB = 0.0;
    for (int i = 0; i < vectorA.length; i++) {
        dotProduct += vectorA[i] * vectorB[i];
        normA += Math.pow(vectorA[i], 2);
        normB += Math.pow(vectorB[i], 2);
    }   
    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
static public double Euclideanorm(double[][] X){
 
 
double sum = 0.0;
for(int i=0; i<X.length; i++) {
for(int j=0; j<X[0].length; j++) {
sum += X[i][j];
}
}
return Math.sqrt(sum);
}
 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
public static double calculateDistance(double[] array1, double[] array2)
{
double Sum = 0.0;
 
for (int i=0;i<array1.length;i++) {
Sum = Sum+Math.pow((array1[i]-array2[i]),2.0);
}
 
return Math.sqrt(Sum);
}
 
 
}