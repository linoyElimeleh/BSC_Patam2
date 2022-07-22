package tests.test6_2020_b_a;

import java.text.DecimalFormat;
import java.util.Random;

public class MainTrain3 {

	private static boolean equals(double[] a, double[] b) {
		if(a.length!=b.length)
			return false;
		for(int i=0;i<a.length;i++)
			if(a[i]!=b[i])
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		// random input
		Random r=new Random();
		int input[]=new int[32768];
		for(int i=0;i<input.length;i++)
			input[i]=-1000+r.nextInt(10001);
		
		// time for bad code
		long bad=System.nanoTime();
		double[] db= BadCode.dists(input);
		bad=System.nanoTime()-bad;
		
		// time for OPT code
		long good=System.nanoTime();
		double[] dg= GoodCode.dists(input);
		good=System.nanoTime()-good;
		
		if(!equals(dg,db)){
			System.out.println("your function did not get the same result (-30)");
			System.out.println("done");
			return;
		}
		
		DecimalFormat f = new DecimalFormat("#,###");
		System.out.println("bad time:\t"+f.format(bad));
		System.out.println("your time:\t"+f.format(good));
		double optRate=bad/good;
		System.out.println("opt rate: "+optRate);
		if(optRate<200)
			System.out.println("you can do better optimizations (-"+(Math.round(30*(200-optRate)/200))+")");
		System.out.println("done");
		
	}


}
