package test;

import java.util.List;

public class GoodCode {

	private static double dist(Point2D a, Point2D b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
	
	private static int within(List<Point2D> ps, Point2D center, int r) {
		int count=0;
		for(Point2D pi : ps) {
			if(dist(center, pi)<=r)
				count++;
		}
		return count;
	}
	
	
	public static int getRad(List<Point2D> ps, Point2D center,int percent) {
		int r=1;
		int p=0;
		while((p=(int)(100.0 * within(ps, center, r) / ps.size())) < percent){
			r++;
		}		
		return r;
	}

}
