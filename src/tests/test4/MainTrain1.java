package tests.test4;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MainTrain1 {

    public static Random r = new Random();


    public static void main(String[] args) {

        // a simple test to get you going:
        // 2*(5-3) = 4
        BE e = new BE('*', new Num(2.0), new BE('-', new Num(5.0), new Num(3.0)));
        ForkJoinPool pool = new ForkJoinPool();
        double result = pool.invoke(e);
        if (result != 4.0) // result should be 4
            System.out.println("expecting result 4, you got " + result + " so (-5)");

        if (MyRecursiveTask.getForkCount() != 1) // only one right branch to compute, so # of forks should be 1
            System.out.println("expecting 1 fork for right branch but you got " + MyRecursiveTask.getForkCount() + " so (-5)");

        // now for 4 randomised tests...
        for (int i = 0; i < 4; i++) {
            MyRecursiveTask.initCounter();

            Expression re = buildRand();
            if (re instanceof BE) {

                ExpTest et = new ExpTest();
                double expectedResult = et.calculate(re, false);
                int expectedForkCount = et.getForkCount();

                result = pool.invoke((BE) re);
                if (result != expectedResult)
                    System.out.println("expecting result " + expectedResult + " you got " + result + " so (-5)");

                if (MyRecursiveTask.getForkCount() != expectedForkCount)
                    System.out.println("expecting " + expectedForkCount + " forks for right branch but you got " + MyRecursiveTask.getForkCount() + " so (-5)");
            }

        }
        System.out.println("done");
    }

    public static Expression buildRand() {
        if (r.nextBoolean())
            return new Num(1 + r.nextInt(100));

        int x = r.nextInt(4);
        switch (x) {
            case 0:
                return new BE('+', buildRand(), buildRand());
            case 1:
                return new BE('-', buildRand(), buildRand());
            case 2:
                return new BE('*', buildRand(), buildRand());
            case 3:
                return new BE('/', buildRand(), buildRand());
        }
        return null;
    }

    public static class ExpTest {
        private int forkCount = 0;

        public double calculate(Expression e, boolean right) {
            if (e instanceof Num)
                return e.calculate();
            if (right)
                forkCount++;
            BE be = (BE) (e);
            switch (be.operator) {
                case '+':
                    return calculate(be.left, false) + calculate(be.right, true);
                case '-':
                    return calculate(be.left, false) - calculate(be.right, true);
                case '*':
                    return calculate(be.left, false) * calculate(be.right, true);
                case '/':
                    return calculate(be.left, false) / calculate(be.right, true);
            }
            return 0;
        }

        public int getForkCount() {
            return forkCount;
        }
    }

}
