package generics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

public class MyThreadPool {

    int maxSize;
    ArrayBlockingQueue<GenericActiveObject> aos;
    GenericActiveObject main;

    public MyThreadPool(int maxSize) {
        this.maxSize = maxSize;
        aos = new ArrayBlockingQueue<>(maxSize);
        main = new GenericActiveObject();
    }

    public void execute(Runnable r) {
        main.execute(() -> assign(r));
    }

    private void assign(Runnable task) {
        if (aos.size() < maxSize) {
            GenericActiveObject ao = new GenericActiveObject();
            ao.execute(task);
            aos.add(ao);
        } else {
            GenericActiveObject min = null;
            int minSize = Integer.MAX_VALUE;
            for (GenericActiveObject ao : aos) {
                if (ao.size < minSize) {
                    minSize = ao.size;
                    min = ao;
                }
            }
            min.execute(task);
        }
    }

    public <V> MyFuture<V> submit(Callable<V> c) {
        MyFuture<V> f = new MyFuture<>();
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
