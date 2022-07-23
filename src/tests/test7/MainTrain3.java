package tests.test7;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.Random;

public class MainTrain3 {

	
	public static void main(String[] args) {
		// random input
		Random r=new Random();
		Point input[]=new Point[10000];
			
		for(int i=0;i<input.length;i++){
			input[i]=new Point();
			input[i].x=-1000+r.nextInt(10001);
			input[i].y=-1000+r.nextInt(10001);
		}
		
		// time for bad code
		long bad=System.nanoTime();
		Point br=BadCode.geoMedian(input);
		bad=System.nanoTime()-bad;
		
		// time for OPT code
		long good=System.nanoTime();
		Point gr=GoodCode.geoMedian(input);
		good=System.nanoTime()-good;
		
		if(gr!=br){
			System.out.println("your function did not get the same result (-30)");
			System.out.println("done");
			return;
		}
		
		DecimalFormat f = new DecimalFormat("#,###");
		System.out.println("bad time:\t"+f.format(bad));
		System.out.println("your time:\t"+f.format(good));
		double optRate=bad/good;
		System.out.println("opt rate: "+optRate);
		if(optRate<4)
			System.out.println("you can do better optimizations (-"+(Math.round(30*(4-optRate)/4))+")");
		System.out.println("done");
		
	}


}
