package tests.test7;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainTrain1 {

    public static void main(String[] args) {
        int initalCount = Thread.activeCount();

        Par p = new Par(10);
        Random r = new Random();
        Integer buff[] = new Integer[10000];
        int sum = 0;
        for (int i = 0; i < buff.length; i++) {
            buff[i] = r.nextInt(1000);
            sum += buff[i];
        }

        Future<Integer> f = p.fold(buff, (x, y) -> x + y);
        if (Thread.activeCount() - initalCount != 1)
            System.out.println("your not using the correct number of threads (-10)");


        Future<List<String>> f2 = p.map(buff, i -> i + "$");
        if (Thread.activeCount() - initalCount != 2)
            System.out.println("your not using the correct number of threads (-5)");

        // test results

        try {
            if (f.get().intValue() != sum)
                System.out.println("you got a wrong result (-5)");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("an exception was thrown (-5)");
        }

        try {
            int i = 0;
            boolean err = false;
            for (String s : f2.get()) {
                if (!s.equals(buff[i] + "$")) {
                    err = true;
                    break;
                }
                i++;
            }
            if (err)
                System.out.println("you got a wrong result (-5)");
        } catch (InterruptedException | ExecutionException e1) {
            System.out.println("an exception was thrown (-5)");
        }

        p.close();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        if (Thread.activeCount() != initalCount)
            System.out.println("you didn't cloase all threads (-10)");

        System.out.println("done");


    }

}
