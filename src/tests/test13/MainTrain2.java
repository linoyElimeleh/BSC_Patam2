package tests.test13;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;

public class MainTrain2 {

    public static void main(String[] args) {
        Random r = new Random();

        int len = 5 + r.nextInt(10);
        int d = 1000000 + r.nextInt(1000000);
        int d1 = r.nextInt(d / 2);
        int check = (d * 2) - (d1 * 2);

        Count c = new Count(len);

        Runnable run = () -> {
            for (int i = 0; i < d; i++)
                c.inc();
        };

        Runnable run1 = () -> {
            for (int i = 0; i < d1; i++)
                c.dec();
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

        for (int i = 0; i < len; i++) {
            if (c.get(i) != d * 2) {
                System.out.println("wrong result inc(-15)");
                System.out.println(c.get(i) + " " + d * 2);
                break;
            } else {
                for (Method m : c.getClass().getMethods()) {
                    if (Modifier.isSynchronized(m.getModifiers()))
                        System.out.println("your not allowed to use synchronized (-15)");
                    break;
                }
            }
        }

        t0 = new Thread(run1);
        t1 = new Thread(run1);
        t0.start();
        t1.start();
        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
        }

        for (int i = 0; i < len; i++) {
            if (c.get(i) != check) {
                System.out.println("wrong result dec(-15)");
                System.out.println(c.get(i) + " " + check);
                break;
            } else {
                for (Method m : c.getClass().getMethods()) {
                    if (Modifier.isSynchronized(m.getModifiers()))
                        System.out.println("your not allowed to use synchronized (-15)");
                    break;
                }
            }
        }
        System.out.println("done");
    }

}
