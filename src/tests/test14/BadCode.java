package tests.test14;

import java.util.LinkedList;
import java.util.Queue;

public class BadCode {
	
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
	
	public static double[] SMA(double[] vec, int window) {
		double[] newVec = new double[vec.length];
		double sum = 0;
		Queue<Double> ds = new LinkedList<Double>();
		
		for (int i = 0; i < vec.length; i++) {
			
			ds.add(vec[i]);
			
			newVec[i] = calcMean(fixSum(ds, window), window);
		}
		return newVec;	
	}
	

}
