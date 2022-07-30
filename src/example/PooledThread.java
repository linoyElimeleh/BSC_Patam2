//package test;
//
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//
//public class PooledThread {
//
//    BlockingQueue<Runnable> q;
//    Thread t;
//    volatile boolean stop = false;
//
//    public PooledThread() {
//        q = new ArrayBlockingQueue<Runnable>(100);
//        t = new Thread(() -> {
//            while (!stop) {
//                try {
//                    q.take().run();
//                    if(q.isEmpty()){
//                        Thread.sleep(1000);
//                        if(!t.isInterrupted()){
//                            stop = true;
//                        }
//                    }
//                } catch (InterruptedException e) {}
//            }
//        });
//        t.start();
//    }
//
//    public void add(Runnable task) {
//        if (!stop)
//            try {
//                q.put(task);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//    }
//
//}
