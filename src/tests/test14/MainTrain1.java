package tests.test14;

import java.util.Arrays;
import java.util.List;

public class MainTrain1 {
	public static void main(String[] args) {
		List<Integer> list=Arrays.asList(1,2,4,6,7,8,9,11,13,15);
		
		ThreadCounter tc=new ThreadCounter();
		tc.start();
		int count = ParallelCounter.parallelCountIf(list, x->x%2==1, 2);
		int numOfThreadsOpened=tc.stopAndGet();
		if(numOfThreadsOpened!=3) 
			System.out.println("wrong number of open threads (-20)");

		if(count!=2)
			System.out.println("wrong number of elements counted (-15)");
		
		System.out.println("done");
	}

}
