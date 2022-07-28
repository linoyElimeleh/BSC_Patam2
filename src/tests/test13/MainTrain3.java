package tests.test13;

import java.util.Arrays;
import java.util.Random;

public class MainTrain3 {

    public static void main(String[] args) {


        Random r = new Random();
        double[] vec = new double[10000];
        for (int i = 0; i < vec.length; i++) {
            vec[i] = (r.nextBoolean() ? 0.3 : Math.random());
        }

        long t0;
        t0 = System.nanoTime();

        double[] smBadOut = BadCode.softmax(vec);

        long duration = System.nanoTime() - t0;

        System.out.println("bad time: " + duration);

        System.out.println();

        t0 = System.nanoTime();
        double[] smGoodOut = GoodCode.softmaxOpt(vec);
        long duration2 = System.nanoTime() - t0;

        System.out.println("your time: " + duration2);

        if (!Arrays.equals(smGoodOut, smBadOut))
            System.out.println("your function does not return the same value as softmax (-35)");


        if (duration2 >= duration / 1.5)
            System.out.println("your function is not optimized enough (-35)");

        System.out.println("done");
    }

}
