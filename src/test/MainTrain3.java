package test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainTrain3 {

	
	public static void main(String[] args) {
		
		// random input
		Random r=new Random();
		List<Point2D> ps=new ArrayList<>();
		for(int i=0;i<100000;i++) {
			ps.add(new Point2D(-100+r.nextInt(201),-100+r.nextInt(201)));
		}
		int percent=50+r.nextInt(21);
		
		// time for bad code
		long bad=System.nanoTime();
		int br=BadCode.getRad(ps, new Point2D(0,0), percent);				
		bad=System.nanoTime()-bad;
		
		
		// time for OPT code
		long good=System.nanoTime();
		int gr=GoodCode.getRad(ps, new Point2D(0,0), percent);
		good=System.nanoTime()-good;
				
		
		if(gr!=br){
			System.out.println("your function did not get the same result (-35)");
			System.out.println(br+","+gr);
			System.out.println("done");
			return;
		}
		
		DecimalFormat f = new DecimalFormat("#,###.##");
		System.out.println("bad time:\t"+f.format(bad));
		System.out.println("your time:\t"+f.format(good));
		double optRate=(double)bad/good;
		System.out.println("opt rate: "+f.format(optRate));
		
		if(optRate<1)
			optRate=1;			
		if(optRate<3)
			System.out.println("you can do better optimizations (-"+(35-Math.round(35*(optRate-1)/2.0))+")");
		System.out.println("done");
		
	}


}


