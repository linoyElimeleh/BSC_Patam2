package tests.test2_2019_b_a;

public class MainTrain2 {
	
	static double avg(double[] x){
		double sum=0;
		for(int i=0;i<x.length;i++)
			sum+=x[i];
		return sum/x.length;
	}
	
	static double pearson(double[] x,double[] y){
		if(x.length==y.length){
			double sumXY=0, sumX=0, sumY=0;
			for(int i=0;i<x.length;i++){
				sumXY+=(x[i]-avg(x))*(y[i]-avg(y));
				sumX+=Math.pow((x[i]-avg(x)),2);
				sumY+=Math.pow((y[i]-avg(y)),2);
			}
			return sumXY/Math.sqrt(sumX*sumY);
		}
		return 0;
	}

	public static void main(String[] args) {
		int n=10000;
		double x[]=new double[n];
		double y[]=new double[n];
		
		for(int i=0;i<n;i++){
			x[i]=Math.random()*10;
			y[i]=(5+Math.random())*x[i]+19+Math.random();
		}
		
		long t0=System.nanoTime();
		double one=pearson(x, x);
		double notOne=((int)(pearson(x,y)*1000))/1000.0;		
		System.out.println("results: p(x,x)="+one+" p(x,y)="+notOne);
		long badTime=System.nanoTime()-t0;
		System.out.println("time: "+ badTime);
		
		Q2.warmup();
		
		t0=System.nanoTime();
		one=Q2.pearson(x, x);
		double notOne1=((int)(Q2.pearson(x,y)*1000))/1000.0;		
		System.out.println("\nresults: p(x,x)="+one+" p(x,y)="+notOne1);
		long goodTime=System.nanoTime()-t0;
		System.out.println("time: "+ goodTime);
		
		try{
			if(one!=1 || notOne1!=notOne){
				System.out.println("your pearson did not get the same result (-50)");
				return;
			}
			if(goodTime>=badTime){
				System.out.println("your pearson is not efficient (-50)");
				return;
			}
			if(goodTime>=0.5*badTime){ 
				System.out.println("your pearson only has a lower bound of 0.5 of the inefficent time (-25)");
				return;
			}
			if(goodTime>=0.25*badTime){
				System.out.println("your pearson only has a lower bound of 0.25 of the inefficent time (-13)");
				return;
			}
			if(goodTime>=0.1*badTime){
				System.out.println("your pearson only has a lower bound of 0.1 of the inefficent time (-5)");
				return;
			}
			if(goodTime>=0.01*badTime){
				System.out.println("your pearson only has a lower bound of 0.01 of the inefficent time (-2)");
				return;
			}
			if(goodTime>=0.001*badTime){
				System.out.println("your pearson only has a lower bound of 0.001 of the inefficent time (-1)");
				return;
			}
			
		}
		finally{
			System.out.println("done");
		}
	}

}
