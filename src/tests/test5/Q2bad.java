package tests.test5;

public class Q2bad {
	
		public static int H(String a, String b){
		int sum=0;
		for(int i=0;i<Math.min(a.toCharArray().length,b.toCharArray().length);i++){
			if(a.toCharArray()[i]!=b.toCharArray()[i])
				sum++;
		}
		
		if(a.length()>b.length())
			sum+=a.length()-b.length();
		
		if(a.length()<b.length())
			sum+=b.length()-a.length();
		
		return sum;
	}
	
	public static int findMinH(String[] array){
		int min=Integer.MAX_VALUE;
		for(String a : array)
			for(String b : array)
				if(a!=b){
					if(min>H(a,b))
						min=H(a,b);
				}
		return min;
	}
}
