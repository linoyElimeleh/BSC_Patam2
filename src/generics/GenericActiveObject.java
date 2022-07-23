package generics;

import java.util.concurrent.*;

public class GenericActiveObject {
    Thread t;
    BlockingQueue<Runnable> q;
    int size;
    volatile boolean stop;


    public GenericActiveObject() {
        q = new ArrayBlockingQueue<>(100);

        t = new Thread(() -> {
            while (!stop) {
                try {
                    q.take().run();
                } catch (InterruptedException e) {
                }
            }
        });
        t.start();
    }

    public void execute(Runnable r) {
        try {
            q.put(r);
        } catch (InterruptedException e) {
        }
    }

    public void shutdownNow() {
        stop = true;
        t.interrupt();
    }

    public void shutdown() {
        execute(() -> stop = true);
    }

    public <V> MyFuture<V> submit(Callable<V> c) {
        MyFuture<V> f = new MyFuture<V>();
        execute(() -> {
            try {
                f.set(c.call());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return f;
    }
}
