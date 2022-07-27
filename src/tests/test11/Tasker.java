package tests.test11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tasker extends HashMap<String, List<Runnable>> {

    private static final long serialVersionUID = 1L;
    volatile boolean stop;
    ArrayList<Thread> threads = new ArrayList<>();

    public Tasker() {

    }

    public void start() {
        stop = true;

        //ExecutorService e=Executors.newFixedThreadPool(keySet().size());
        for (String key : keySet()) {
            Thread t = new Thread(() -> {
                for (Runnable r : get(key)) {
                    // e.execute(r);
                    r.run();
                }
            });
            t.setName(key);
            threads.add(t);
            t.start();
        }


        // e.execute();
    }

    @Override
    public List<Runnable> put(String key, List<Runnable> runnablelist) {
        if (!stop) {
            return super.put(key, runnablelist);
        }
        return null;
    }

    public void join() {
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        });
    }
}
