package tests.test13;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Count {
    AtomicIntegerArray countArr;


    public Count(int size) {
        countArr = new AtomicIntegerArray(size);

        //Arrays.fill(countArr, 0);
    }

    public void inc() {
        for (int i = 0; i < countArr.length(); i++)
            countArr.getAndIncrement(i);
    }

    public void dec() {
        for (int i = 0; i < countArr.length(); i++)
            countArr.getAndDecrement(i);
    }

    public int get(int index) {
        return countArr.get(index);
    }
}
