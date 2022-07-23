package tests.test6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainTrain1 {

	public static void main(String[] args) {
		Random r=new Random();
		List<Integer> buff=new ArrayList<>();
		for(int i=0;i<320000;i++)
			buff.add(r.nextInt(100));
		
		int c=Thread.activeCount();
		PTasker pt=new PTasker();
		Future<Integer> f=pt.apply(buff, 0, (x,y)->x+y);
		int sum=0;
		try {
			sum=f.get();
			//System.out.println("sum: "+sum);
		} catch (InterruptedException | ExecutionException e) {}
		if(Thread.activeCount()-c!=1)
			System.out.println("wrong number of threads opened (-15)");
		int sumt=0;
		for(Integer i : buff)
			sumt+=i;
		if(sumt!=sum)
			System.out.println("wrong output (-15)");
		pt.close();
		if(Thread.activeCount()-c!=1)
			System.out.println("some threads are still open (-5)");
		
		System.out.println("done");
	}

}
