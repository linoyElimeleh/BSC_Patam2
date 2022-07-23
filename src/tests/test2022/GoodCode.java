package tests.test2022;
public class GoodCode {


	/*
	I Don't need it, I can calculate the sum one time (the sum of vec array don't change)

	public static double expSum(double[] vec){
			double sum=0;
			for(int i=0;i<vec.length;i++)
				sum+= Math.exp(vec[i]);
			return sum;
	}

	public static double exp(double z){
		return Math.exp(z);
	}
	*/

	public static double[] softmaxOpt(double[] vec){
		double resultExpSum=0;
		double[] prop=new double[vec.length];
		for(int i=0;i<vec.length;i++) //Calculation of expSum with function
		{
			resultExpSum+= Math.exp(vec[i]);
		}
		for(int i=0;i<vec.length;i++)
		{
			//Calc of function exp
			prop[i]=Math.exp(vec[i])/resultExpSum;
		}
		return prop;
	}

}
