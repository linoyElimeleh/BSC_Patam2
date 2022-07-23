package tests.test10;

public class MainTrain1 {

    public static void main(String[] args) {
        MyTimer t = new MyTimer();

        for (int i = 0; i < 2; i++) { // two times

            int threads = Thread.activeCount();

            int sum[] = {0};

            try {
                t.start(() -> {
                    sum[0]++;
                }, 2);
            } catch (Exception e) {
            }

            if (threads + 1 != Thread.activeCount())
                System.out.println("start should run a thread (-5)");

            boolean exp = false;
            try {
                t.start(() -> {
                    sum[0]++;
                }, 2);
            } catch (Exception e) {
                exp = true;
                if (!e.getMessage().equals("this timer already runs a task"))
                    System.out.println("the exception doesn't have the right message (-5)");
            }
            if (!exp)
                System.out.println("start should have thrown an exception (-5)");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            t.stop();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

            if (threads != Thread.activeCount())
                System.out.println("stop should have stopped the thread (-5)");

            // sum[0] should be no more than 3
            if (sum[0] > 3)
                System.out.println("you did not run the task correctly (-5)");
        }


        System.out.println("done");

    }

}
