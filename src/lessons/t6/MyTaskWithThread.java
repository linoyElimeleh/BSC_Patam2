package lessons.t6;

public class MyTaskWithThread extends Thread {
    // we could extend just one time in a class

    int x;
    volatile boolean stop;

    public MyTaskWithThread(int x) {
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
