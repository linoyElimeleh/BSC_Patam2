package lessons.t11;

import java.util.concurrent.RecursiveTask;

public class ParMaxSearcher extends RecursiveTask<Integer> {

    BinTree tree;

    public ParMaxSearcher(BinTree tree) {
        this.tree = tree;
    }

    @Override
    protected Integer compute() {
        if (tree.getLeft() == null && tree.getRight() == null) {
            return tree.getV();
        }

        ParMaxSearcher l = new ParMaxSearcher(tree.getLeft());
        ParMaxSearcher r = new ParMaxSearcher(tree.getRight());
        l.fork();

        return Math.max(tree.getV(), Math.max(r.compute(), l.join()));
    }
}
