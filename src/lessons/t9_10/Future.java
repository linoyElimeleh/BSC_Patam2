package lessons.t9_10;

import java.util.function.Consumer;
import java.util.function.Function;

public class Future<V> {
    V v;
    Runnable task;

    public synchronized void set(V v) {
        this.v = v;
        task.run();
        notifyAll();
    }

    public V getV() throws InterruptedException {
        if (v == null) {
            synchronized (this) {
                if (v == null) {
                    wait();
                }
            }
        }
        return v;
    }

    // template <class R>
    public <R> Future<R> thenApply(Function<V, R> f) {
        Future<R> fr = new Future<>();
        task = () -> fr.set(f.apply(v));
        return fr;
    }

    public void thenAccept(Consumer<V> c) {
        task = () -> c.accept(v);
    }
}
