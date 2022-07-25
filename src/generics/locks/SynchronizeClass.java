package generics.locks;

public class SynchronizeClass {

    // just one threads could get into the synchronized methods


    // everybody have data member to each instance
    private boolean instancLock = false;
    private static boolean classLock = false;
    private Object objectLock = false;


    // if the methods not static-> we cant get into the current function. each lock have diffenrent instance;
    synchronized void function() {

    }

    // if the method is static -> in the all class we cant get into the all synchronized classes
    synchronized static void functionStatic() {

    }


    void functionBlock() {
        synchronized (objectLock) {

        }
    }


    void functionThis() {
        synchronized (this) {

        }
    }

    // which lock we want to lock
}
