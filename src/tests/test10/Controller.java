package tests.test10;

public class Controller {

    private static int count = 0;

    Controller() {
        count++;
    }

    public static int getCount() {
        return count;
    }

}
