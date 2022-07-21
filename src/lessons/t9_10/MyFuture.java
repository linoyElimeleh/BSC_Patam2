package lessons.t9_10;

public class MyFuture<V> extends Future<V> {
    public synchronized void set(V v) {
        this.v = v;
        notifyAll();
    }
}
