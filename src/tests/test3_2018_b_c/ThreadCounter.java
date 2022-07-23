package tests.test3_2018_b_c;

public class ThreadCounter extends Thread {

	volatile boolean stop=false;
	volatile int count=0;
	
	public void run(){
		while(!stop){
			if(Thread.activeCount()>count)
				count=Thread.activeCount();
		}
	}
	
	public int stopAndGet(){
		stop=true;
		return count-1;
	}
}
