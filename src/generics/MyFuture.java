package generics;

import java.util.function.Consumer;
import java.util.function.Function;

public class MyFuture<V> {
    V v;
    Runnable r;

    public synchronized void set(V v) {
        this.v = v;
        if (r != null) {
            r.run();
        }
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
    public <R> MyFuture<R> thenApply(Function<V, R> f) {
        MyFuture<R> fr = new MyFuture<>();
        r = () -> fr.set(f.apply(v));
        return fr;
    }

    public void thenAccept(Consumer<V> c) {
        r = () -> c.accept(v);
    }
}
