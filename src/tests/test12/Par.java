package tests.test12;

import lessons.t11.ParMaxSearcher;

import java.util.concurrent.RecursiveTask;

public class Par extends RecursiveTask<Integer> {
    BinTree tree;

    public Par(BinTree tree) {
        this.tree = tree;
    }

    @Override
    protected Integer compute() {
        if (tree.getLeft() == null && tree.getRight() == null) {
            return tree.get();
        }

        Par l = new Par(tree.getLeft());
        Par r = new Par(tree.getRight());
        l.fork();

        return tree.get() + r.compute() + l.compute();
    }
}