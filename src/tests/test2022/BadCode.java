package tests.test2022;
public class BadCode {
	
	public static double expSum(double[] vec){
		double sum=0;
		for(int i=0;i<vec.length;i++)
			sum+= exp(vec[i]);
		return sum;
	}
	
	public static double exp(double z){
		return Math.exp(z);
	}
	
	public static double[] softmax(double[] vec){
		double[] prop=new double[vec.length];
		for(int i=0;i<vec.length;i++) {
			prop[i]=exp(vec[i])/expSum(vec);
		}
		return prop;		
	}
	

}
