package tests.test3_2018_b_c;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Q2 {

    public static <V> AtomicInteger parallelCountIf(List<V> list, Predicate<V> p, int size) {

        int eachThreadSize = list.size() / size;

        Thread threads[] = new Thread[size];

        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < size; i++) {
            List<V> subList = list.subList(i * eachThreadSize, (i + 1) * eachThreadSize);
            threads[i] = new Thread(() ->
                    subList.forEach(x -> {
                        if (p.test(x)) {
                            count.incrementAndGet();
                            System.out.println(count);
                        }
                    }));

            threads[i].start();
        }

        return count;
    }


}
