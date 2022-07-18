package lessons.t6;

public class MyTaskForRunnableTask  {

    int x;
    volatile boolean stop;

    public MyTaskForRunnableTask(int x) {
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

    public int getX() {
        return x;
    }
}
