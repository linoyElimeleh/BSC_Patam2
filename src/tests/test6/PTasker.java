package tests.test6;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.BinaryOperator;

public class PTasker {
    volatile boolean stop = false;
    ExecutorService es = Executors.newSingleThreadExecutor();

    public <V> Future<V> apply(List<V> buff, V identity, BinaryOperator<V> bo) {
        if (stop) {
            return null;
        }

        return es.submit(() -> {
            V result = identity;
            for (V element : buff) {
                result = bo.apply(result, element);
            }
            return result;
        });

    }

    public void close() {
        stop = true;
        es.shutdown();
    }
}
