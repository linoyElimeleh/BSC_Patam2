package tests.test5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;

public class Q1c {

    public static <T> T recThis(ExecutorService es, T[] array, int start, int end, BinaryOperator<T> f) throws Exception {

        if (end - start == 1)
            return f.apply(array[start], array[end - 1]);

        int mid = (end + start) / 2;

        //task 1 : start-mid
        Callable<T> c = () -> recThis(es, array, start, mid, f);
        Future<T> left = es.submit(c);

        //task 2 : mid-end
        T right = recThis(es, array, mid, end, f);
        return f.apply(left.get(), right);
    }
}
