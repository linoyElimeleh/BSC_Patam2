package tests.test11;

import java.util.Random;

public class BinTree {
	int v;
	
	BinTree left=null;
	BinTree right=null;
	
	private BinTree(int v) {
		this.v=v;
	}
	
	
	public int get() {
		return v;
	}

	public BinTree getLeft() {
		return left;
	}

	public BinTree getRight() {
		return right;
	}
	
	
	
	private static Random r=new Random();
	private static int max=0;
	private static boolean called=false;
	public static int getMax() throws Exception{
		if(called)
			throw new Exception("you are not allowed to call getMax");
		called = true;
		return max;
	}
	
	public static BinTree generateRandomTree(int depth){
		int x=r.nextInt(10000);
		if(x>max)
			max=x;
		
		BinTree root=new BinTree(x);

		if(depth>0) {
			BinTree left=generateRandomTree(depth-1);
			BinTree right=generateRandomTree(depth-1);
			root.left = left;
			root.right = right;
		}
		return root;
	}
}
