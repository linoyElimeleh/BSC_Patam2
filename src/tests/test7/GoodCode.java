package tests.test7;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GoodCode {
    private static double EucDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static Point geoMedian(Point[] values) {
        Point r = null;
        double min = Double.MAX_VALUE;


        double sumx = 0, sumy = 0, avgx, avgy;
        for (int i = 0; i < values.length; i++) {
            sumx += values[i].x;
            sumy += values[i].y;
        }

        avgx = sumx / values.length;
        avgy = sumy / values.length;


        for (Point a : values) {
            double sum = 0;

            if (Math.abs(avgx - a.x) < 1000 && Math.abs(avgy - a.y) < 1000) {
                for (Point b : values) {
                    sum += EucDistance(a, b);
                }
                if (min > sum) {
                    min = sum;
                    r = a;
                }
            }
        }
        return r;
    }
}
