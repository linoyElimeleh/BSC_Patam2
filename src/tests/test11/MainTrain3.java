package tests.test11;

import java.util.ArrayList;
import java.util.Random;

public class MainTrain3 {

    public static void main(String[] args) {
        Random r = new Random();
        ArrayList<Double> data = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            data.add(20 * +r.nextDouble());
        }

        long time0 = System.nanoTime();
        Q3bad<Double> bad = new Q3bad<>(data);
        double rb = bad.fold(1.0, (x, y) -> x * y);
        long time1 = System.nanoTime();

        long badTime = time1 - time0;

        time0 = System.nanoTime();
        Q3good<Double> good = new Q3good<>(data);
        double rg = good.fold(1.0, (x, y) -> x * y);
        time1 = System.nanoTime();
        long goodTime = time1 - time0;

        if (rg > rb + 0.01 || rg < rb - 0.01)
            System.out.println("you didn't get the same result (-50)");
        else {
            System.out.println("bad time: " + badTime);
            System.out.println("your time: " + goodTime);
            long ratio = Math.max(badTime / goodTime, 0);
            System.out.println("ratio: " + ratio);
            if (ratio < 18)
                System.out.println("you can do better optimiztions (-" + (35 - 35 * ratio / 18) + ")");
        }

        System.out.println("done");
    }

}
