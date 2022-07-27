package tests.test12;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainTrain2 {

    public static void main(String[] args) {
        BinTree tree = BinTree.generateRandomTree(10);
        int tsum = 0;
        try {
            tsum = BinTree.getSum();
        } catch (Exception e) {
        }

        Par par = new Par(tree);
        ForkJoinPool fjp = new ForkJoinPool();
        Future<Integer> sum = fjp.submit(par);

        fjp.shutdown();
        boolean ok = true;
        try {
            ok = fjp.awaitTermination(1, TimeUnit.SECONDS);
            if (!ok) {
                System.out.println("your program is stuck (-31)");
                fjp.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("you got an exception while waiting for termination (-31)");
        }

        if (ok) {
            try {
                if (sum.get() != tsum)
                    System.out.println("you didn't return the correct sum value (-20)");
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("exception in future get (-20)");
            }

            if (fjp.getStealCount() <= 1)
                System.out.println("you didn't use the threads correctly (-11)");
        }

        System.out.println("done");
    }

}
