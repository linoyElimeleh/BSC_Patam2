package lessons.t7.a;

import java.util.concurrent.*;

public class ThreadPoolExample {

    public static class ExampleTask implements Runnable {

        int id;

        // Constructor
        public ExampleTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Start task " + id);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task finished " + id);
        }
    }

    public static class ExampleTask2 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        }
    }

    public static void main(String[] args) {

        // Active object- contains just one task! task one after one!
        ExecutorService esSingle = Executors.newSingleThreadExecutor();

        // A lot of threads. and also reuse for the old threads. not the good way
        ExecutorService esCache = Executors.newCachedThreadPool();

        // Number of threads we want to run. This thread not dead -it waits for the new task.
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(new ExampleTask(1));
        es.execute(new ExampleTask(2));
        es.execute(new ExampleTask(3));
        es.execute(new ExampleTask(4)); // Run when the first task is dead
        es.execute(new ExampleTask(5)); // Run when the second task is dead

        Future<Integer> f = es.submit(new ExampleTask2()); // Wait in the line. return future
        System.out.println("...");
        try {
            Integer i = f.get();
            System.out.println(Thread.currentThread().getName() + " returned value is " + i.intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Shutdown - wait for the tasks is done!
        // ShutdownNow- close all right now
        es.shutdown();


        System.out.println("Main is dead");
    }
}
