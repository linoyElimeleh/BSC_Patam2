package tests.test12;
import java.util.Random;

public class MainTrain3 {
	private static boolean stop=false;
	private static boolean slept=false;
	static void isSleeping(Thread t) {
		new Thread(()-> {
			while(!stop) {
				if(t.getState().equals(Thread.State.TIMED_WAITING)) {
					slept=true;
					stop=true;
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		
		isSleeping(Thread.currentThread());
		
		Random r=new Random();
		boolean[][] data=new boolean[10000][];		
		for(int i=0;i<data.length;i++) {
			data[i]=new boolean[10000];
			for(int j=0;j<data[i].length;j++) {
				data[i][j]=r.nextBoolean();
			}
		}
		
		long time0=System.nanoTime();
		boolean rb = Q3bad.vote(data);
		long time1=System.nanoTime();
		
		long badTime=time1-time0;
		
		time0=System.nanoTime();		
		boolean rg = Q3good.vote(data);
		time1=System.nanoTime();
		long goodTime=time1-time0;
		
		stop=true;
		
		if(rb!=rg)
			System.out.println("you didn't get the same result (-35)");
		else {
			System.out.println("bad time: "+badTime);
			System.out.println("your time: "+goodTime);
			long ratio=Math.max(badTime/goodTime,0);			
			System.out.println("ratio: "+ratio);
			if(ratio<9)
				System.out.println("you can do better optimiztions (-"+(35-35*ratio/9)+")");
			if(ratio>50 || slept)
				System.out.println("wrong answer (-35)");
		}
		
		System.out.println("done");
	}	

}
