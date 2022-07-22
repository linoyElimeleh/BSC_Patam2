package lessons.t12_13;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainTrain {

    public static void main(String[] args) {
        Random r = new Random();
        List<Point> ps = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            ps.add(new Point(-1000 + r.nextInt(2001), -1000 + r.nextInt(2001)));
        }

        long bad = System.nanoTime();
        Point br = Bad.minSqrSum(ps);
        bad = System.nanoTime() - bad;

        long good = System.nanoTime();
        Point gr = Good.minSqrSum(ps);
        good = System.nanoTime() - good;

        if (gr != br) {
            System.out.println("error");
        }

        DecimalFormat f = new DecimalFormat("#,###.##");
        System.out.println("bad time" + f.format(bad));
        System.out.println("good time" + f.format(good));

        double optRate = (double) bad / good;
        System.out.println("opt rate" + f.format(optRate));
        if (optRate < 4) {
            System.out.println("error opti");
        }
        System.out.println("done");
    }
}
