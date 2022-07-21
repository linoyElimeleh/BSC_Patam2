package lessons.t9_10;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {
    BlockingQueue<ActiveObject> activeObjects;
    BlockingQueue<Runnable> mainQueue;
    Thread t;
    volatile boolean stop;
    Runnable stopAll = () -> stop = true;

    public MyThreadPool(int capacity, int maxThreads) {
        activeObjects = new ArrayBlockingQueue<>(maxThreads);
        mainQueue = new ArrayBlockingQueue<>(capacity);

        t = new Thread(() -> {
            while (!stop) {
                try {
                    Runnable task = mainQueue.take();

                    if (task == stopAll) {
                        stopAll.run();// or stop=true
                    } else {
                        if (activeObjects.size() < maxThreads) {
                            ActiveObject ac = new ActiveObject(capacity);
                            ac.execute(task);
                            activeObjects.add(ac);
                        } else {
                            ActiveObject choice = chooseSmallest();
                            choice.execute(task);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

    private ActiveObject chooseSmallest() {
        ActiveObject choice = null;
        int min = Integer.MAX_VALUE;

        for (ActiveObject a : activeObjects) {
            if (a.size() < min) {
                min = a.size();
                choice = a;
            }
        }

        return choice;
    }

    public void execute(Runnable r) throws InterruptedException {
        if (!stop) {
            mainQueue.put(r);
        }
    }

    public void shutdownNow() {
        stop = true;
        activeObjects.forEach(a -> a.shutdownNow());
        t.interrupt();
    }

    public void shutdown() throws InterruptedException {
        activeObjects.forEach(a -> a.shutdownNow());
        execute(stopAll);
    }

    public <V> Future<V> submit(Callable<V> c) throws InterruptedException {
        Future<V> f = new Future<>();
        execute(() -> f.set(c.call()));
        return f;
    }
}
