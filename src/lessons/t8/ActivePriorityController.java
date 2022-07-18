package lessons.t8;

import java.util.concurrent.PriorityBlockingQueue;

public class ActivePriorityController {

    private class PCommand {
        public Command c;
        public int priority;

        public PCommand(Command c, int priority) {
            this.c = c;
            this.priority = priority;
        }
    }

    PriorityBlockingQueue<PCommand> q;
    Thread t;
    volatile boolean stop; // volatile - is one source of true . one instance only

    public ActivePriorityController() {

        // Check priority- like str compare
        q = new PriorityBlockingQueue<PCommand>(100, (a, b) -> a.priority - b.priority);
        t = new Thread(() -> {
            while (!stop) {
                try {
                    q.take().c.execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void addCommand(Command c, int priority) {
        q.add(new PCommand(c, priority));
    }

    public void close() {
        // one option
//        stop = true;
//        t.interrupt();

        // second option
        // one more command
        q.add(new PCommand(() -> stop = true, Integer.MAX_VALUE));
    }
}
