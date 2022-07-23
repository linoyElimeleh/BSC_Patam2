package tests.test9_2021_a_a;

public class MainTrain1 {

    public static void main(String[] args) {
        int tx = Thread.activeCount();
        Pipe<String> ps = new Pipe<String>();
        int sum[] = {0};
        // sum all lengths of strings with length under 4
        ps.filter(s -> s.length() < 4).map(s -> s.length()).forEach(x -> sum[0] += x);
        // now the should be 3 additional threads waiting for input
        if (Thread.activeCount() - tx != 3)
            System.out.println("wrong number of threads oppend (-10)");

        ps.add("ABC");        //3
        ps.add("ABCDE");    // x
        ps.add("ABCD");    // x
        ps.add("ABC");        // 3
        ps.add("ABCD");    // x
        ps.add("ABCDE");    // x
        ps.add("AB");        // 2

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }

        if (sum[0] != 8) // 3+3+2
            System.out.println("wrong end result (-10)");

        ps.stop();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        if (Thread.activeCount() != tx)
            System.out.println("wrong number of threads closed (-15)");

        System.out.println("done");
    }

}
