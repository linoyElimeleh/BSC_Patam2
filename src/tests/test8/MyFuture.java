package tests.test8;


import java.util.function.Consumer;
import java.util.function.Function;

public class MyFuture<V> {
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
	public <R> MyFuture<R> thenDo(Function<V, R> f) {
		MyFuture<R> fr = new MyFuture<>();
		task = () -> fr.set(f.apply(v));
		return fr;
	}

	public void finallyDo(Consumer<V> c) {
		task = () -> c.accept(v);
	}
}
