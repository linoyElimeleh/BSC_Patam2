package lessons.t6;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        // How many threads do you have
        System.out.println(Thread.activeCount());

        // Current Tread name
        System.out.println(Thread.currentThread().getName());

        // Start the tread
        MyTaskWithThread t = new MyTaskWithThread(0);
        t.start();
        Thread.sleep(1000);
        t.stopMe();
        System.out.println("x= " + t.getX());
        System.out.println("done");

        // Start the runnable
        MyTaskWithRunnable tr = new MyTaskWithRunnable(0);
        Thread t0 = new Thread(tr);
        t0.start();
        Thread.sleep(1000);
        tr.stopMe();
        System.out.println("x= " + tr.getX());
        System.out.println("done");

        // Runnable task
        MyTaskForRunnableTask trr = new MyTaskForRunnableTask(0);
        Thread t2 = new Thread(new RunnableTask(trr));
        t2.start();
        Thread.sleep(1000);
        trr.stopMe();
        System.out.println("x= " + trr.getX());
        System.out.println("done");

        // Runnable here
        MyTaskForRunnableTask trrr = new MyTaskForRunnableTask(0);
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                trrr.doAction();
            }
        });
        t3.start();
        Thread.sleep(1000);
        trrr.stopMe();
        System.out.println("x= " + trrr.getX());
        System.out.println("done");


        // Runnable lambda expression
        MyTaskForRunnableTask trrrr = new MyTaskForRunnableTask(0);
        Thread t4 = new Thread(() -> trrrr.doAction());
        t4.start();
        Thread.sleep(1000);
        trrrr.stopMe();
        System.out.println("x= " + trrrr.getX());
        System.out.println("done");
    }
}

