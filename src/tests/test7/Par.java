package tests.test7;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Par {
    int size;
    ExecutorService es;

    public Par(int size) {
        this.size = size;
        es = Executors.newFixedThreadPool(size);
    }


    public <V> Future<V> fold(V[] buffer, BinaryOperator<V> operator) {
        Callable<V> callable = () -> {
            V v = buffer[0];
            for (int i = 1; i < buffer.length; i++) {
                v = operator.apply(v, buffer[i]);
            }
            return v;
        };

        return es.submit(callable);
    }

    public <V, R> Future<List<R>> map(V[] buffer, Function<V, R> function) {
        Callable<List<R>> c = (() -> {
            List<R> r = new ArrayList<>();
            for (int i = 0; i < buffer.length; i++) {
                r.add(function.apply(buffer[i]));
            }
            return r;
        });

        return es.submit(c);
    }

    public void close() {
        es.shutdown();
    }
}
