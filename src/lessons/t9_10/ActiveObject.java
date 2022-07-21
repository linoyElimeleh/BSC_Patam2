package lessons.t9_10;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ActiveObject {
    BlockingQueue<Runnable> q;
    Thread t;
    volatile boolean stop; //between threads

    public ActiveObject(int capacity) {
        q = new ArrayBlockingQueue<Runnable>(capacity);

        t = new Thread(() -> {
            while (!stop) {
                try {
                    q.take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

    public void execute(Runnable r) throws InterruptedException {
        if (!stop) {

            // q.add(r); // return if success true or false
            q.put(r); // wait until it success to input
            // we can execute right now by r.run;
        }
    }

    public void shutdownNow() {
        stop = true;
        t.interrupt();
    }

    public void shutdown() throws InterruptedException {
        // stop= true -> right now finished. by we want when the queue is finished.
        execute(() -> stop = true);
    }

    public int size() {
        return q.size();
    }
}
