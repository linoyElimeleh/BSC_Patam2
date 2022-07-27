package tests.test12;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTrain1 {

	public static void main(String[] args) {
		Random r=new Random();

		final int x=r.nextInt(10000);
		
		ExecutorService es=Executors.newSingleThreadExecutor();
		
		long t0=System.currentTimeMillis();
		ObservableFuture<Integer> of=new ObservableFuture<>(es.submit(()->{
			Thread.sleep(1000); // like a long task
			return x;
		}));
		
		if(System.currentTimeMillis()-t0>=1000)
			System.out.println("ObservableFuture should be returned immediately (-10)");

		Integer i = of.get();
		if(System.currentTimeMillis()-t0>=1000 || i!=null)
			System.out.println("get method should not wait for V (-10)");
		
		
		boolean[] ok= {true};
		of.addObserver((o,a)->{			
			if(of.get()!=x)
				ok[0]=false;
		});
		
		of.addObserver((o,a)->{
			if(System.currentTimeMillis()-t0<1000)
				System.out.println("observers should be notified only when the value is ready (-4)");
		});
		
		
		try {Thread.sleep(1100);} catch (InterruptedException e) {}
		
		if(!ok[0])
			System.out.println("the observer did not get the right value (-10)");
		
		es.shutdown();
		
		System.out.println("done");
		
	}

}
