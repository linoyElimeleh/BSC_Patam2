package test;

public class MainTrain1 {

    public static void main(String[] args) {
        int count = Thread.activeCount();
        PooledThread pt = new PooledThread();
        if (Thread.activeCount() != count + 1) {
            System.out.println("your PooledThread object did not open a new thread (-30)");
            System.out.println("done");
            return;
        }

        int sum[] = {0};
        for (int i = 0; i < 10; i++)
            pt.add(() -> {
                sum[0] += 10;
                //System.out.println(sum[0]);
            });

        // wait for 1.5 second
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }
        // by now your pooled thread should close

        if (Thread.activeCount() != count)
            System.out.println("your PooledThread object did not close its thread (-10)");

        if (sum[0] != 100)
            System.out.println("your PooldThread did not run the tasks (-10)");

        System.out.println("done");
    }

}
