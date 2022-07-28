package tests.test14;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainTrain2 {

	public static void main(String[] args) {
		Random r=new Random();
		int num = r.ints(2,25).findFirst().getAsInt();
		LucasNumbers luc = new LucasNumbers(num);
		List<Integer> lnl = Arrays.asList(2,1,3,4,7,11,18,29,47,76,123,199,322,521,843,1364,2207,3571,5778,9349,15127,24476,39603,64079,103682,167761);
		
		ForkJoinPool fjp=new ForkJoinPool();
		Future<Integer> lucasNum=fjp.submit(luc);
		
		fjp.shutdown();
		boolean ok=true;
		try {
			ok=fjp.awaitTermination(1, TimeUnit.SECONDS);
			if(!ok) {
				System.out.println("your program is stuck (-30)");
				fjp.shutdownNow();
			}
		} catch (InterruptedException e) {
			System.out.println("you got an exception while waiting for termination (-30)");
		}
		int check = lnl.get(num);
		
		if(ok) {
			try {
				if(lucasNum.get()!=check)
					System.out.println("you didn't return the correct Lucas Number (-20)");
			} catch (InterruptedException | ExecutionException e) {
				System.out.println("exception in future get (-20)");
			}
			
			if(fjp.getStealCount()<=1)
				System.out.println("you didn't use the threads correctly (-10)");
		}
		
		System.out.println("done");
	}

}
