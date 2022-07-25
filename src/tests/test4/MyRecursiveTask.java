package tests.test4;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyRecursiveTask<V> extends RecursiveTask<V> {

	private static final long serialVersionUID = 1L;
	
	private static AtomicInteger count=new AtomicInteger(0);
	
	
	public ForkJoinTask<V>  testFork(){
		count.incrementAndGet();
		return super.fork();
	}
	
	public static int getForkCount(){
		return count.intValue();
	}

	public static void initCounter() {
		count.set(0);
	}

}
