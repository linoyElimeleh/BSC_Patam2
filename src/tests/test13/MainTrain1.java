package tests.test13;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainTrain1 {

    public static void main(String[] args) throws InterruptedException {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();


        TaskerList t = new TaskerList();
        BlockingQueue<Runnable> b1 = new ArrayBlockingQueue<Runnable>(4);
        b1.add(() -> sb1.append(".1.").append("b1").reverse());
        b1.add(() -> sb1.append(".2.").append("b1").reverse());
        b1.add(() -> sb1.append(".3.").append("b1").reverse());
        b1.add(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        });

        t.addLast(b1);

        BlockingQueue<Runnable> b2 = new ArrayBlockingQueue<Runnable>(4);
        b2.add(() -> sb2.append(".1.").append("b2").reverse());
        b2.add(() -> sb2.append(".2.").append("b2").reverse());
        b2.add(() -> sb2.append(".3.").append("b2").reverse());
        b2.add(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        });

        t.addLast(b2);

        t.pollAll();

        t.stopRunning();

        // does not put anything after stop running()
        BlockingQueue<Runnable> b3 = new ArrayBlockingQueue<Runnable>(3);
        b3.add(() -> sb3.append(".1.").append("b3").reverse());
        b3.add(() -> sb3.append(".2.").append("b3").reverse());
        b3.add(() -> sb3.append(".3.").append("b3").reverse());

        t.addLast(b3);

        if (Thread.activeCount() != 1)
            System.out.println("you didn't close your threads correctly (-10)");

        if (!sb1.toString().equals("1b.3.1b.1..2.b1"))
            System.out.println("wrong result for thread t1 (-10)");

        if (!sb2.toString().equals("2b.3.2b.1..2.b2"))
            System.out.println("wrong result for thread t2 (-10)");

        if (t.contains(b3))
            System.out.println("b3 should not run (-5)");

        System.out.println("done");
    }

}
