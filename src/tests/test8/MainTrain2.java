package tests.test8;

import java.util.ArrayList;
import java.util.Random;

public class MainTrain2 {

	public static void main(String[] args) {
		Random r=new Random();
		ArrayList<Double> X=new ArrayList<>(),Y=new ArrayList<>();
		for(int i=0;i<1000;i++) {
			double x=1+r.nextInt(100);			
			X.add(20*x+15+r.nextDouble());
			Y.add(x);
		}
		
		long time0=System.nanoTime();
		Q2bad bad=new Q2bad(X, Y);
		double ce=bad.conditionalEntropy();
		long time1=System.nanoTime();
		
		long badTime=time1-time0;
		
		time0=System.nanoTime();
		Q2good good=new Q2good(X, Y);
		double sce=good.conditionalEntropy();
		time1=System.nanoTime();
		long goodTime=time1-time0;
		
		if(sce>ce+0.01 || sce<ce-0.01)
			System.out.println("you didn't get the same result (-50)");
		else {
			System.out.println("bad time: "+badTime);
			System.out.println("your time: "+goodTime);
			long ratio=Math.max(badTime/goodTime,0);			
			System.out.println("ratio: "+ratio);
			if(ratio<10)
				System.out.println("you can do better optimiztions (-"+(50-50*ratio/10)+")");			
		}
		
		System.out.println("done");
	}	

}
