package tests.test14;
import java.util.concurrent.RecursiveTask;

public class LucasNumbers extends RecursiveTask<Integer> {
	int num;
	public LucasNumbers(int num) {
		this.num = num;
	}
	
	public Integer compute() {
		if(num == 0) {return 2;}
		if(num == 1) { return 1;}
		
		LucasNumbers luc1 = new LucasNumbers(num - 1);
		luc1.fork();
		
		LucasNumbers luc2 = new LucasNumbers(num - 2);
		
		return (luc2.compute() + luc1.join());
	}
	
}
