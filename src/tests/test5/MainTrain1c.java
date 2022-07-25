package tests.test5;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTrain1c {

	public static void main(String[] args) {
		// random init of the array
		Random r=new Random();
		Integer[] array=new Integer[32];
		int max=Integer.MIN_VALUE;
		for(int i=0;i<array.length;i++){
			array[i]=r.nextInt();
			if(max<array[i])
				max=array[i];
		}
		
		int tc=Thread.activeCount();
		ExecutorService es=Executors.newCachedThreadPool();
		try {
			Integer rmax = Q1c.recThis(es, array, 0, array.length, (Integer x,Integer y)->(x>y?x:y));
			if(rmax!=max)
				System.out.println("recThis did not return the correct value (-10)");
			if(Thread.activeCount()-tc<5)
				System.out.println("you did not open the correct number of threads (-10)");
		} catch (Exception e) {
			System.out.println("you have some runtime Exception (-20)");
		}		
		es.shutdown();
		System.out.println("done");
	}

}
