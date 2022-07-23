package tests.test10;

public class MyTimer {
    Thread t;
    volatile boolean stop;

    public void start(Runnable r, int timesPerSecond) throws Exception {
        if (t != null) {
            throw new Exception("this timer already runs a task");
        }
        t = new Thread(() -> {
            while (!stop) {
                try {
                    Thread.sleep(1000 / timesPerSecond);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                r.run();
            }
        });
        t.start();
    }

    public void stop() {
        stop = true;
        t.interrupt();
        t = null;
    }
}
