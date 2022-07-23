package tests.test2022;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Count {

	AtomicIntegerArray atomicIntegerArrayCount;

	public Count(int size) {
		this.atomicIntegerArrayCount = new AtomicIntegerArray(size);
	}

	public  void inc() {
		for (int i = 0; i < this.atomicIntegerArrayCount.length(); i++) {
			this.atomicIntegerArrayCount.incrementAndGet(i);
		}
	}

	public  void dec() {
		for (int i = 0; i < this.atomicIntegerArrayCount.length(); i++) {
			this.atomicIntegerArrayCount.decrementAndGet(i);
		}
	}

	public int get(int index) {
		return atomicIntegerArrayCount.get(index);
	}
}
