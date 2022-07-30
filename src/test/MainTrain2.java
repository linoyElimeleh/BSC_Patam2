package test;

public class MainTrain2 {

    public static void main(String[] args) throws Exception {
        int tx = Thread.activeCount();
        MyStream<String> ms = new MyStream<String>();
        int sum[] = {0};
        // sum all lengths of strings with length under 4
        ms.filter(s -> s.length() < 4).map(s -> s.length()).forEach(x -> sum[0] += x);
        // now the should be 3 additional threads waiting for input
        if (Thread.activeCount() - tx != 3)
            System.out.println("wrong number of threads oppend (-10)");

        ms.add("ABC");        //3
        ms.add("ABCDE");    // x
        ms.add("ABCD");    // x
        ms.add("ABC");        // 3
        ms.add("ABCD");    // x
        ms.add("ABCDE");    // x
        ms.add("AB");        // 2

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }

        if (sum[0] != 8) // 3+3+2
            System.out.println("wrong end result (-10)");

        ms.stop();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        if (Thread.activeCount() != tx)
            System.out.println("wrong number of threads closed (-15)");

        System.out.println("done");
    }
}
