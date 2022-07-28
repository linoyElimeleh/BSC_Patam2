package tests.test14;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ParallelCounter {
	

	public static <E> int parallelCountIf(List<E> list, Predicate<E> p, int numOfThreads) {
		
		Thread[] threads = new Thread[numOfThreads];
		AtomicInteger atomicSum = new AtomicInteger(0);
		
		int listLength = list.size();
		int sizeOfSubLists = listLength / numOfThreads;
		
		int indexSubStart = 0;
		int indexSubEnd = sizeOfSubLists;
		
		for (int i = 0; i < numOfThreads; i++) {

			List<E> subList = list.subList(indexSubStart, indexSubEnd);
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {			
					
					for (E e : subList) {
						if (p.test(e)) {
							atomicSum.incrementAndGet(); // atomicSum++
						} 
						else {
							atomicSum.decrementAndGet(); // atomicSum--
						}		
					}	
				}
			});
			threads[i].start();
			
			indexSubStart += sizeOfSubLists;
			indexSubEnd += sizeOfSubLists;
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();				
			} catch (InterruptedException e) {}
		}
		return atomicSum.get();
	}
	
	


}
