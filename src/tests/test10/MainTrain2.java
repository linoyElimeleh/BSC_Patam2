package tests.test10;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainTrain2 {

    public static void main(String[] args) {
        if (Controller.getCount() != 0)
            System.out.println("you should not create Controller Objects if not asked (-10)");

        Thread ts[] = new Thread[10];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(() -> {
                Controller c = ControllerMaker.get();
                if (Controller.getCount() != 1)
                    System.out.println("you should have only one Controller (-1)");
            });
        }

        for (Thread t : ts)
            t.start();

        try {
            for (Thread t : ts)
                t.join();
        } catch (InterruptedException e) {
        }

        for (Method m : ControllerMaker.class.getMethods()) {
            if (Modifier.isSynchronized(m.getModifiers())) {
                System.out.println("your not allowed to use synchronized (-5)");
                break;
            }
        }


        System.out.println("done");
    }

}
