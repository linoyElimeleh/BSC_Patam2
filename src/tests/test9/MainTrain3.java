package tests.test9;
import java.text.DecimalFormat;
import java.util.Random;

public class MainTrain3 {

	
	public static void main(String[] args) {
		// random input
		Random r=new Random();
		int[] grades=new int[100000];
		for(int i=0;i<grades.length;i++)
			grades[i]=r.nextInt(101);
		
		// time for bad code
		long bad=System.nanoTime();
		int br=BadCode.common(grades);
		bad=System.nanoTime()-bad;
		
		// time for OPT code
		long good=System.nanoTime();
		int gr=GoodCode.common(grades);
		good=System.nanoTime()-good;
		
		if(gr!=br){
			System.out.println("your function did not get the same result (-35)");
			System.out.println("done");
			return;
		}
		
		DecimalFormat f = new DecimalFormat("#,###");
		System.out.println("bad time:\t"+f.format(bad));
		System.out.println("your time:\t"+f.format(good));
		double optRate=bad/good;
		System.out.println("opt rate: "+optRate);		
		if(optRate<5)
			System.out.println("you can do better optimizations (-"+(Math.round(35*(5-optRate)/5))+")");
		System.out.println("done");
		
	}


}
