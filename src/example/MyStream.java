package example;

import test.Stoppable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<E> implements Stoppable {

    Thread t;
    BlockingQueue<E> queue;
    volatile boolean stop;
    Stoppable next;

    public MyStream() {
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


    public MyStream<E> filter(Predicate<E> predicate) {
        MyStream<E> next = new MyStream<>();
        consumer = e -> {
            if (predicate.test(e)) {
                next.add(e);
            }
        };
        this.next = next;
        return next;
    }

    public <R> MyStream<R> map(Function<E, R> function) {
        MyStream<R> next = new MyStream<>();
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