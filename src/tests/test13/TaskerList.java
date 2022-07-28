package tests.test13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class TaskerList extends LinkedList<BlockingQueue<Runnable>> {

    private static final long serialVersionUID = 1L;

    volatile boolean stop = false;
    ArrayList<Thread> threads = new ArrayList<>();


    public void pollAll() {

        forEach((queues) -> {

            Thread t = new Thread(() -> {
                while (!queues.isEmpty())
                    try {
                        queues.take().run();
                    } catch (InterruptedException e) {
                    }
            });
            t.start();
            threads.add(t);
        });
    }

    @Override
    public void addLast(BlockingQueue<Runnable> e) {
        if (!stop)
            super.addLast(e);
    }

    public void stopRunning() throws InterruptedException {
        this.stop = true;
        threads.forEach(t -> t.interrupt());
    }
}
