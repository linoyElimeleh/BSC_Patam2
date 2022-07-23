package tests.test10;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoodCode {

    public static double dist(Point a, Point b) {
        return ((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public static Set<Point> inCircles(Circle[] cs, List<Point> ps) {
        HashSet<Point> hs = new HashSet<>();

        for (Point pi : ps) { // for each point
            for (Circle ci : cs) { // for each circle
                if (dist(pi, ci.center) <= ci.r * ci.r) {
                    hs.add(pi);
                    break;
                }

            }
        }
        return hs;
    }

}
