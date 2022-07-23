package tests.test10;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BadCode {
	

	public static double dist(Point a,Point b) {
		return Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
	}
	
	public static Set<Point> inCircles(Circle[] cs, List<Point> ps){
		HashSet<Point> hs=new HashSet<>();
		for(Circle ci : cs) { // for each circle
			for(Point pi : ps) { // for each point
				if(dist(pi, ci.center)<=ci.r)
					hs.add(pi);
			}
		}
		return hs;
	}

}
