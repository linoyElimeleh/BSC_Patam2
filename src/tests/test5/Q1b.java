package tests.test5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Q1b {
    BlockingQueue<Runnable> blockingQueue;
    Thread t;
    volatile boolean stop;

    public Q1b() {
        blockingQueue = new ArrayBlockingQueue<>(100);

        t = new Thread(() -> {
            try {
                while (!stop)
                    blockingQueue.take().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    public void push(Runnable r) {
        try {
            if (!stop)
                blockingQueue.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        push(() -> stop = true);
    }
}