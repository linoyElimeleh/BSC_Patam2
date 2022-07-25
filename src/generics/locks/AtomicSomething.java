package generics.locks;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSomething {

    // Atomic function-
    // we use this in multi thread when we want to lock this member
    // example:
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


}
