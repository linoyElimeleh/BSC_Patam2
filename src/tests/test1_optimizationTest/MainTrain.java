package tests.test1_optimizationTest;

import java.util.Random;

public class MainTrain {

	public static void main(String[] args) {
		Random r=new Random();
		double[] vec=new double[10000];
		for(int i=0;i<vec.length;i++){
			vec[i]=(r.nextBoolean()?0.3:Math.random());
		}
		
		long t0;
		t0=System.nanoTime();
		double hxr=Math.round(Q1.Hx(vec)*1000)/1000.0;
		long duration=System.nanoTime()-t0;
		System.out.println("result= "+hxr);
		System.out.println("time: "+duration);
		
		System.out.println();
		
		t0=System.nanoTime();
		double optr=Math.round(Q2.OPT_Hx(vec)*1000)/1000.0;
		long duration2=System.nanoTime()-t0;
		System.out.println("OPT result= "+optr);
		System.out.println("OPT time: "+duration2);
		
		if(hxr!=optr)
			System.out.println("your function does not return the same value as hx (-50)");
		

		if(duration2>=duration/1.5)
			System.out.println("your function is not optimized enough (-50)");
		
		System.out.println("done");
	}

}
