package test;

import java.util.concurrent.*;

public class PooledThread {
    BlockingQueue<Runnable> q;
    volatile boolean stop;
    Thread t;

    public PooledThread() {
        q = new ArrayBlockingQueue<>(100);

        t = new Thread(() -> {
            while (!stop) {
                try {
                    q.take().run();
                    if (q.isEmpty()) {
                        Thread.sleep(1000);
                        if (!t.isInterrupted()) {
                            stop = true;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void add(Runnable task) {
        q.add(task);
    }
}
