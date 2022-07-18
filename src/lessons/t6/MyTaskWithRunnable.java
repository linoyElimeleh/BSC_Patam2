package lessons.t6;

public class MyTaskWithRunnable implements Runnable {
    // We can implement how long that we want

    int x;
    volatile boolean stop;

    public MyTaskWithRunnable(int x) {
        this.x = x;
    }

    public void doAction() {
        System.out.println(Thread.currentThread().getName());
        while (!stop) {
            x++;
        }
    }

    public void stopMe() {
        stop = true;
    }

    @Override
    public void run() {
        doAction();
    }

    public int getX() {
        return x;
    }
}
