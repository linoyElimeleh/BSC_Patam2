package tests.test11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainTrain2 {

    public static void main(String[] args) {
        BinTree tree = BinTree.generateRandomTree(10);
        int tmax = 0;
        try {
            tmax = BinTree.getMax();
        } catch (Exception e) {
        }

        ParMaxSearcher searcher = new ParMaxSearcher(tree);
        ForkJoinPool fjp = new ForkJoinPool();
        Future<Integer> max = fjp.submit(searcher);

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
                if (max.get() != tmax)
                    System.out.println("you didn't return the correct max value (-20)");
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("exception in future get (-20)");
            }

            if (fjp.getStealCount() <= 1)
                System.out.println("you didn't use the threads correctly (-11)");
        }

        System.out.println("done");
    }

}
