package tests.test12;

import java.util.Observable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ObservableFuture<V> extends Observable {
    // implement CTOR
    V futureE;

    public ObservableFuture(Future<V> future) {
        new Thread(() -> {
            try {
                futureE = future.get();
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            }
            setChanged();
            notifyObservers();
        }).start();
    }

    public V get() {
        return futureE;
    }
}
