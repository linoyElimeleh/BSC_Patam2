package test;

public class MainTrain1 {

	public static void main(String[] args) {
		final int sum[]={0};
		MyFuture<String> mf=new MyFuture<>();		
		mf.thenDo(s->Integer.parseInt(s)).thenDo(x->x*2).thenDo(x->sum[0]+=x).finallyDo(x->System.out.println("result1: "+x));
		mf.set("42");
		if(sum[0]!=84)
			System.out.println("you didn't get the correct result (-17)");
		
		sum[0]=0;
		MyFuture<Double> mf1=new MyFuture<>();
		mf1.thenDo(d->d.intValue()).thenDo(x->x*3).thenDo(x->sum[0]+=x).finallyDo(x->System.out.println("result2: "+x));
		mf1.set(42.3);
		if(sum[0]!=126)
			System.out.println("you didn't get the correct result (-17)");
		
		sum[0]=0;
		MyFuture<Character> mf2=new MyFuture<>();
		mf2.thenDo(c->c.charValue()-'0').thenDo(x->x*10+2).thenDo(x->sum[0]+=x).finallyDo(x->System.out.println("result3: "+x));
		mf2.set('4');
		if(sum[0]!=42)
			System.out.println("you didn't get the correct result (-16)");
		
		
		System.out.println("done");
	}

}
