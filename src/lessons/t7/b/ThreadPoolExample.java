package lessons.t7.b;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class ThreadPoolExample {

    public static class ExampleTask2 implements Callable<Integer>, Supplier<Integer> {

        @Override
        public Integer call() throws Exception { // Callable throws exceptions
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        }

        @Override
        public Integer get() { // Supplier don't throw an exception
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        CompletableFuture
                .supplyAsync(new ExampleTask2(), es)
                .thenApply(x -> x * 2)
                .thenApply(x -> "The value is: " + x)
                .thenAccept(s -> System.out.println(s));

        es.shutdown();

        System.out.println("Main is dead");
    }
}
