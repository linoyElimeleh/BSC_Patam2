package tests.test9_2021_a_a;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Pipe<E> implements Stoppable {
    Thread t;
    BlockingQueue<E> queue;
    volatile boolean stop;
    Stoppable next;

    public Pipe() {
        queue = new ArrayBlockingQueue<>(100);
        t = new Thread(() -> {
            while (!stop) {
                try {
                    if (consumer != null) {
                        E e = queue.take();
                        consumer.accept(e);
                    }
                } catch (InterruptedException e) {
                }
            }
        });
        t.start();
    }


    public Pipe<E> filter(Predicate<E> predicate) {
        Pipe<E> next = new Pipe<>();
        consumer = e -> {
            if (predicate.test(e)) {
                next.add(e);
            }
        };
        this.next = next;
        return next;
    }

    public <R> Pipe<R> map(Function<E, R> function) {
        Pipe<R> next = new Pipe<>();
        consumer = e -> {
            next.add(function.apply(e));
        };
        this.next = next;
        return next;
    }

    Consumer<E> consumer;

    public void forEach(Consumer<E> consumer) {
        this.consumer = consumer;
    }

    public void add(E e) {
        try {
            queue.put(e);
        } catch (InterruptedException d) {

        }
    }

    // and stop
    @Override
    public void stop() {
        stop = true;
        t.interrupt();
        if (next != null) {
            next.stop();
        }
    }
}