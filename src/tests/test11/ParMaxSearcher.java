package tests.test11;

import java.util.concurrent.RecursiveTask;

public class ParMaxSearcher extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;

    BinTree root;

    public ParMaxSearcher(BinTree tree) {
        this.root = tree;
    }


    @Override
    protected Integer compute() {
        if (root.getLeft() != null && root.getRight() != null) {
            ParMaxSearcher left = new ParMaxSearcher(root.getLeft());
            left.fork();
            ParMaxSearcher right = new ParMaxSearcher(root.getRight());
            int max = Math.max(right.compute(), left.join());
            return Math.max(root.get(), max);
        } else
            return root.get();
    }

}
