package lessons.t9_10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    return "42";
                }, es)
                .thenApply(s -> Integer.parseInt(s))
                .thenApply(x -> x * 2).thenAccept(ans -> System.out.println("the result is " + ans));
        es.shutdown();
        System.out.println("Main is dead.");
    }
}
