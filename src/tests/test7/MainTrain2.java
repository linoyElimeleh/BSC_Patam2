package tests.test7;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MainTrain2 {

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        ForkJoinPool fj = new ForkJoinPool();

        if (fj.invoke(new FJ_GCD(18, 27)) != 9)
            System.out.println("you got a wrong result (-11)");

        Random r = new Random();
        int x = r.nextInt(1000) * 2;
        if (fj.invoke(new FJ_GCD(x, 2)) != 2)
            System.out.println("you got a wrong result (-12)");

        x = r.nextInt(1000);
        int y = r.nextInt(100);

        if (fj.invoke(new FJ_GCD(x, y)) != gcd(x, y))
            System.out.println("you got a wrong result (-12)");


        System.out.println("done");
    }

}
