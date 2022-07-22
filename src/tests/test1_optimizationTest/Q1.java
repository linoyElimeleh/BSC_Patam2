package tests.test1_optimizationTest;

public class Q1 {
	
	public static double px(double x, double[] vec){
		int count=0;
		for(int i=0;i<vec.length;i++)
			if(x==vec[i])
				count++;
		return (double)count/vec.length;
	}
	
	public static double log2(double x){
		return Math.log10(x)/Math.log10(2);
	}
	public static double Hx(double[] vec){
		double sum=0;
		for(int i=0;i<vec.length;i++)
			sum+=px(vec[i],vec)*log2(px(vec[i],vec));
			return -sum;		
	}
	

}
