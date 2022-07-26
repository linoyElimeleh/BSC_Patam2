package tests.test5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Q1a {

    ExecutorService es;

    public Q1a() {
        es = Executors.newSingleThreadExecutor();
    }

    public void close() {
        es.shutdown();
    }

    public <V> Future<V> threadIt(Callable<V> call) {
        return es.submit(call);
    }
}
