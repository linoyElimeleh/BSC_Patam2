package tests.test11;

import java.util.Arrays;

public class MainTrain1 {

	public static void main(String[] args) {
		StringBuffer sb1=new StringBuffer();		
		StringBuffer sb2=new StringBuffer();		
		StringBuffer sb3=new StringBuffer();		

		Tasker t=new Tasker();
		
		t.put("t1",Arrays.asList(
				()->sb1.append(".1.").append(Thread.currentThread().getName()) , 
				()->sb1.append(".2.").append(Thread.currentThread().getName()) , 
				()->sb1.append(".3.").append(Thread.currentThread().getName())  
				));
		
		t.put("t2",Arrays.asList(
				()->sb2.append(".1.").append(Thread.currentThread().getName()) , 
				()->sb2.append(".2.").append(Thread.currentThread().getName()) , 
				()->sb2.append(".3.").append(Thread.currentThread().getName())  
				));
		
		t.start();
		
		// does not put anything after start()
		t.put("t3",Arrays.asList(
				()->sb1.append(".1.").append(Thread.currentThread().getName()) , 
				()->sb3.append(".2.").append(Thread.currentThread().getName()) , 
				()->sb3.append(".3.").append(Thread.currentThread().getName())  
				));
		
		t.join(); // waits for all threads to finish (t1, t2)
		
		if(!sb1.toString().equals(".1.t1.2.t1.3.t1"))
			System.out.println("wrong result for thread t1 (-12)");

		if(!sb2.toString().equals(".1.t2.2.t2.3.t2"))
			System.out.println("wrong result for thread t2 (-12)");
		
		if(sb3.length()>0)
			System.out.println("t3 should not run after start (-10)");
		
		System.out.println("done");
		
	}

}
