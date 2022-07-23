package tests.test9_2021_a_a;

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
