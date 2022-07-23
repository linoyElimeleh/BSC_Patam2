package tests.test9;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;

public class MainTrain2 {

    public static void main(String[] args) {
        Random r = new Random();
        int d = 1000000 + r.nextInt(1000000);
        Count c = new Count();
        Runnable run = () -> {
            for (int i = 0; i < d; i++)
                c.inc();
        };
        Thread t0 = new Thread(run);
        Thread t1 = new Thread(run);
        t0.start();
        t1.start();
        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
        }

        if (c.get() != d * 2) {
            System.out.println("wrong result (-30)");
            System.out.println(c.get() + " " + d * 2);
        } else {
            for (Method m : c.getClass().getMethods()) {
                if (Modifier.isSynchronized(m.getModifiers()))
                    System.out.println("your not allowed to use synchronized (-30)");
            }
        }
        System.out.println("done");
    }

}
