package tests.test2;

public class MainTrain1 {

	public static void main(String[] args) {
		int ac=Thread.activeCount();
		int len=600;
		boolean array[]=new boolean[len];

		ParallelStreamer<Integer> ps=new ParallelStreamer<>(5, 100, i->array[i]=true);
		
		try{
			for(int i=0;i<len;i++)			
				ps.add(i);
		}catch(InterruptedException e){}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		// by this time all threads should be standing by waiting for input
		
		if(Thread.activeCount()!=ac+5)
			System.out.println("wrong number of opened threads (-15)");
		
		boolean b=true;
		for(int i=0;i<len && b;i++)
			b=b && array[i];
		
		if(!b)
			System.out.println("problem in processing data (-20)");
			
		ps.endOfInput();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		
		if(Thread.activeCount()!=ac)
			System.out.println("you didn't close your threads correctly (-15)");

		System.out.println("done");
	}

}
