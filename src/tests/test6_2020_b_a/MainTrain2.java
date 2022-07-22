package tests.test6_2020_b_a;

;

public class MainTrain2 {

	public static int ac=0,bc=0,cc=0;
	
	public static class TestA{
		public TestA() {
			ac++;
		}
	}
	public static class TestB{
		public TestB() {
			bc++;
		}
	}
	public static class TestC{
		public TestC() {
			cc++;
		}
	}
	
	public static void main(String[] args) {
		
		boolean exp[]={false};
		
		Thread[] t=new Thread[4];
		
		for(int i=0;i<t.length;i++){
			t[i]=new Thread(()->{
				try {
					TestA a=Singleton.getInstance(TestA.class);
					TestB b=Singleton.getInstance(TestB.class);
					TestC c=Singleton.getInstance(TestC.class);
				} catch (Exception e) {
					exp[0]=true;
				}
			});
			t[i].start();
		}
		
		for(int i=0;i<t.length;i++)
			try {t[i].join();} catch (InterruptedException e) {}
		
		if(exp[0])
			System.out.println("you got some runtime exception (-35)");
		else{
			if(ac!=1)
				System.out.println("wrong object count of type TestA (-12)");
			if(bc!=1)
				System.out.println("wrong object count of type TestB (-12)");
			if(cc!=1)
				System.out.println("wrong object count of type TestC (-11)");
		}
		System.out.println("done");
	}

}
