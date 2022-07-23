package tests.test10;

import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainTrain3 {


    public static void main(String[] args) {
        // random input
        Random r = new Random();
        List<Point> ps = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            ps.add(new Point(-1000 + r.nextInt(2001), -100 + r.nextInt(201)));
        }
        Circle cs[] = new Circle[10 + r.nextInt(10)];
        for (int i = 0; i < cs.length; i++)
            cs[i] = new Circle(new Point(-1000 + r.nextInt(2001), -100 + r.nextInt(201)), 50 + r.nextInt(50));

        // time for bad code
        long bad = System.nanoTime();
        Set<Point> bd = BadCode.inCircles(cs, ps);
        bad = System.nanoTime() - bad;

        // time for OPT code
        long good = System.nanoTime();
        Set<Point> gd = GoodCode.inCircles(cs, ps);
        good = System.nanoTime() - good;

        if (!gd.containsAll(bd) || !bd.containsAll(gd)) {
            System.out.println("your function did not get the same result (-35)");
            System.out.println("done");
            //return;
        }

        DecimalFormat f = new DecimalFormat("#,###.##");
        System.out.println("bad time:\t" + f.format(bad));
        System.out.println("your time:\t" + f.format(good));
        double optRate = (double) bad / good;
        System.out.println("opt rate: " + f.format(optRate));
        if (optRate < 2)
            System.out.println("you can do better optimizations (-" + (Math.round(35 * (2.0 - optRate) / 2.0)) + ")");
        System.out.println("done");

    }


}
