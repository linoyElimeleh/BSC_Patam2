package lessons.t11;

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
                System.out.println("Your program is stuck..");
                fjp.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Exception..");
        }

        if (ok) {
            try {
                if (max.get() != tmax)
                    System.out.println("You didnt return correct value..");
            } catch (Exception e) {
                System.out.println("Exception in future get..");
            }
        }
        System.out.println("Done");
    }
}
