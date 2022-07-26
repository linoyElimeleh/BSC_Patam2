package tests.test5;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Maintrain2 {

    public static void main(String[] args) {
        // random input
        Random r = new Random();
        String input[] = new String[1024];
        ArrayList<String> input2 = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            int len = 100;
            if (r.nextFloat() < 0.3)
                len = 5 + r.nextInt(len - 5);

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++)
                if (r.nextBoolean())
                    sb.append("1");
                else
                    sb.append("0");

            input[i] = sb.toString();
            input2.add(input[i]);
        }

        // time for bad code
        long bad = System.nanoTime();
        int bmin = Q2bad.findMinH(input);
        bad = System.nanoTime() - bad;

        // time for OPT code
        long good = System.nanoTime();
        int gmin = Q2.findMinH(input2);
        good = System.nanoTime() - good;

        if (bmin != gmin) {
            System.out.println("your function did not get the same result (-50)");
            System.out.println("bad min H dist: " + bmin);
            System.out.println("your min H dist: " + gmin);
            System.out.println("done");
            return;
        }

        DecimalFormat f = new DecimalFormat("#,###");
        System.out.println("bad time:\t" + f.format(bad));
        System.out.println("your time:\t" + f.format(good));
        double optRate = bad / good;
        System.out.println("opt rate: " + optRate);
        if (optRate < 50)
            System.out.println("you can do better optimizations (-" + (50 - optRate) + ")");
        System.out.println("done");

    }

}
