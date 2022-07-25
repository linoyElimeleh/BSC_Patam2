//package tests.test5;
//
//import java.util.concurrent.Future;
//
//public class MainTrain1a {
//
//	public static void delay(long time){
//		try {Thread.sleep(time);} catch (InterruptedException e) {}
//	}
//
//	public static void main(String[] args) {
//		// Q1a
//		Q1a q=new Q1a();
//		int tc=Thread.activeCount();
//		double x=Math.random()*10;
//		Future<Double> f=q.threadIt(()->{delay(1000); return x;});
//		if(Thread.activeCount()-tc!=1)
//			System.out.println("threadIt should open only one thread (-6)");
//
//		try {
//			if(f.get().doubleValue()!=x)
//				System.out.println("the Future did not return the correct value (-6)");
//		} catch (Exception e) {
//			System.out.println("problem with the Future object (-6)");
//		}
//		q.close();
//
//		System.out.println("done");
//	}
//
//}
