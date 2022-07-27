package tests.test4;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.function.BiFunction;

public class BE extends MyRecursiveTask<Double> implements Expression {

    private static final long serialVersionUID = 1L;
    Expression left;
    Expression right;
    public char operator;
    public BiFunction<Double, Double, Double> f;

    public BE(char op, Expression left, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = op;
        f = (l, r) -> {
            switch (operator) {
                case '+':
                    return l + r;
                case '-':
                    return l - r;
                case '*':
                    return l * r;
                case '/':
                    return l / r;
                default:
                    return 0.0;
            }
        };
    }

    @Override
    public Double calculate() {
        return compute();
    }

    @Override
    protected Double compute() {
        if (this.right instanceof BE) {
            ForkJoinTask<Double> t = ((BE) this.right).testFork();

            try {
                return this.f.apply(left.calculate(), t.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return this.f.apply(left.calculate(), right.calculate());
    }
}
