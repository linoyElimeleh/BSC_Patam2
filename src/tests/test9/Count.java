package tests.test9;

import java.util.concurrent.atomic.AtomicInteger;

public class Count {
    AtomicInteger count;

    public Count() {
        count = new AtomicInteger(0);
    }

    public void inc() {
        count.incrementAndGet();
    }

    public int get() {
        return count.get();
    }
}
