package tests.test6_2020_b_a;

public class SingeltonTest {
    private static SingeltonTest singeltonTest;

    private SingeltonTest() {

    }

    public synchronized static SingeltonTest getInstance() {
        if (singeltonTest == null) {
            singeltonTest = new SingeltonTest();
        }

        return singeltonTest;
    }
}
