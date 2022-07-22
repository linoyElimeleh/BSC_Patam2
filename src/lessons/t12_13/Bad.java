package lessons.t12_13;

import java.util.List;

public class Bad {

    private static double EucDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private static double sumDist(Point a, List<Point> ps) {
        double sum = 0;
        for (Point pi : ps) {
            sum += Math.pow(EucDistance(a, pi), 2);
        }
        return sum;
    }

    public static Point minSqrSum(List<Point> ps){
        double min= Double.MAX_VALUE;
        Point minPoint=null;
        for(Point pi: ps){
            if(min>sumDist(pi,ps)){
                min=sumDist(pi,ps);
                minPoint=pi;
            }
        }
        return minPoint;
    }
}
