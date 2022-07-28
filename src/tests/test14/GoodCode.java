package tests.test14;

import java.util.LinkedList;
import java.util.Queue;

public class GoodCode {
	
	public static double fixSum(Queue<Double> ds, int window) {
		int sum = 0;
		if (ds.size() > window) 
			ds.remove();
		
		for (Double num : ds) 
			sum += num;
			
		return sum;	
	}
	
	public static double calcMean(double sum, double window) {
		return sum/window;
	}
	
	public static double[] SMA_Opt(double[] vec, int window) {
		int vecLen = vec.length;
		double[] newVec = new double[vecLen];
		double sum = 0;
		Queue<Double> ds = new LinkedList<Double>();
		
		for (int i = 0; i < vecLen; i++) {
			sum += vec[i];
			ds.add(vec[i]);
			
			if (ds.size() > window) 
				sum -= ds.remove();
			
			newVec[i] = sum/window;
		}
		return newVec;
		
	}
	
	 
}
