package tests.test5;

public class MainTrain1b {
	
	public static void delay(long time){
		try {Thread.sleep(time);} catch (InterruptedException e) {}
	}

	public static void main(String[] args) {
		// Q1b
		int tc=Thread.activeCount();
		Q1b q=new Q1b();
		int[] sum={0};
		for(int i=0;i<10;i++)
			q.push(()->{delay(100);sum[0]+=1;});
		if(Thread.activeCount()-tc!=1)
			System.out.println("The Active Object should open only one thread (-6)");
		
		delay(1500);
		q.close();
		delay(100);
		if(Thread.activeCount()!=tc)
			System.out.println("close method did not close the thread (-6)");
		
		q.push(()->sum[0]+=1);
		
		if(sum[0]!=10)
			System.out.println("problem detected in running all the runnable tasks (-6)");		
	
		
		System.out.println("done");
	}	

}
