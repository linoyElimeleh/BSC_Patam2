package lessons.t12_13;

import java.util.List;

public class Good {
    static double min;
    static Point minPoint= null;
    private static double EucDistance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    private static double sumDist(Point a, List<Point> ps) {
        double sum = 0;
        for (Point pi : ps) {
            sum += EucDistance(a, pi);
            if (sum > min) {
                return Double.MAX_VALUE;
            }
            else {
                min = sum;
                minPoint = pi;
            }
        }
        return sum;
    }

    public static Point minSqrSum(List<Point> ps) {
        min = Double.MAX_VALUE;
        for (Point pi : ps) {
             sumDist(pi, ps);
        }
        return minPoint;
    }
}
