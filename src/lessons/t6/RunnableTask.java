package lessons.t6;

public class RunnableTask implements Runnable {

    MyTaskForRunnableTask t;

    public RunnableTask(MyTaskForRunnableTask t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.doAction();
    }
}

