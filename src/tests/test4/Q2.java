package tests.test4;

public class Q2 {

	static double avg(double[] x){
		double sum=0;
		for(int i=0;i<x.length;i++)
			sum+=x[i];
		return sum/x.length;
	}
	
	static double[] averageCols(double[][] data){
		double avg[]=new double[data[0].length];
		for(int col=0;col<data[0].length;col++){
			double column[]=new double[data.length];
			for(int row=0;row<data.length;row++){
				column[row]=data[row][col];
			}
			avg[col]=avg(column);
		}
		return avg;
	}
	

	
}
