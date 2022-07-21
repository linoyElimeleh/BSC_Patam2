package lessons.t11;

import java.util.Random;

public class BinTree {
    int v;

    private BinTree(int v) {
        this.v = v;
    }

    public int getV() {
        return v;
    }

    public BinTree getLeft() {
        return left;
    }

    public BinTree getRight() {
        return right;
    }

    BinTree left = null;
    BinTree right = null;


    private static Random r = new Random();
    private static int max = 0;
    private static boolean called = false;

    public static int getMax() throws Exception {
        if (called)
            throw new Exception("You arrent allowed");
        called = true;
        return max;
    }


    public static BinTree generateRandomTree(int depth) {
        int x = r.nextInt(10000);
        if (x > max)
            max = x;

        BinTree root = new BinTree(x);

        if (depth > 0) {
            BinTree left = generateRandomTree(depth - 1);
            BinTree right = generateRandomTree(depth - 1);
            root.left = left;
            root.right = right;
        }
        return root;
    }
}
