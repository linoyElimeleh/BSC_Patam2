package tests.test6;

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
