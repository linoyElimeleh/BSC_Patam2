package tests.test7;

import java.awt.Point;

public class BadCode {
	
	private static double EucDistance(Point a, Point b){
		return Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
	}
	
	public static Point geoMedian(Point[] values){
		Point r=null;
		double min=Double.MAX_VALUE;
		for(Point a: values){
			double sum=0;
			for(Point b : values){
				sum+=EucDistance(a, b);
			}
			if(min>sum){
				min=sum;
				r=a;
			}
		}
		return r;
	}

}
